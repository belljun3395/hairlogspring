package jongjun.hairlog.app.domain.usecase.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jongjun.hairlog.app.config.AppConfig;
import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.app.exception.AlreadyEditException;
import jongjun.hairlog.app.web.controller.converter.MemberControllerConverter;
import jongjun.hairlog.app.web.controller.request.member.MemberRequest;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
class EditMemberFacadeUseCaseTest {

	@Autowired SaveMemberUseCase saveMemberUseCase;
	@Autowired EditMemberFacadeUseCase editMemberFacadeUseCase;
	@Autowired GetMemberUseCase getMemberUseCase;
	@Autowired MemberControllerConverter memberControllerConverter;
	@Autowired MemberRepository memberRepository;

	@Test
	void concurrencyTest() throws InterruptedException {
		String targetEmail = "testEmail";
		Long targetCycle = 100L;
		memberRepository.deleteAllInBatch();
		MemberRequest member =
				MemberRequest.builder()
						.email(targetEmail)
						.password("testPassword")
						.name("testName")
						.cycle(targetCycle)
						.sex(MemberSex.M)
						.build();
		Long targetMemberId = saveMemberUseCase.execute(memberControllerConverter.to(member));

		int threadCount = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(threadCount);
		Random random = new Random();

		for (int i = 0; i < threadCount; i++) {
			EditMemberRequest editMember =
					EditMemberRequest.builder()
							.id(targetMemberId)
							.cycle(random.nextLong(targetCycle))
							.build();
			executorService.submit(
					() -> {
						try {
							editMemberFacadeUseCase.execute(editMember);
						} finally {
							latch.countDown();
						}
					});
		}

		latch.await();

		assertThatThrownBy(() -> getMemberUseCase.execute(targetMemberId))
				.isInstanceOf(AlreadyEditException.class);
		assertThat(getMemberUseCase.execute(targetEmail).getCycle()).isNotEqualTo(targetCycle);
	}
}
