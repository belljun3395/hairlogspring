package jongjun.hairlog.data.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import jongjun.hairlog.data.converter.IdConverter;
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
@Table(name = "designer_entity")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE designer_entity SET deleted = true WHERE member_fk=?")
@Where(clause = "deleted=false")
@NamedNativeQuery(
		name = "DesignerEntity.findDeletedDesignersEntity",
		query =
				"select d.designer_id, d.designer_name, d.designer_salon from designer_entity d where d.member_fk = ?1")
public class DesignerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "designer_id")
	@Convert(converter = IdConverter.class)
	private Long id;

	@Column(name = "designer_name")
	private String designerName;

	@Column(name = "designer_salon")
	private String designerSalon;

	@Column(name = "designer_fav")
	private Boolean designerFav;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_fk")
	private MemberEntity member;

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
