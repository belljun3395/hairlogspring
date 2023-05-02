package jongjun.hairlog.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import jongjun.hairlog.data.enums.MemberSex;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@ToString
@Builder(toBuilder = true)
@Table(name = "member_entity")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE member_entity SET deleted = true WHERE member_id=?")
@Where(clause = "deleted=false")
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "member_email")
	private String email;

	@Column(name = "member_password")
	private String password;

	@Column(name = "member_name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "member_sex")
	private MemberSex sex;

	@Column(name = "member_cycle")
	private Long cycle;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;
}
