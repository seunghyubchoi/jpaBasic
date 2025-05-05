package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member extends BaseEntity {
    // 기본 키 매핑
    @Id

    // @GeneratedValue : 직접 할당하는 게 아니라면 사용

    // IDENTITY : 기본 키 생성을 데이터베이스에 위임(persist 하면 바로 insert 쿼리)
    // DB에 PK 값이 있어야 하기 때문에 그리고 나서 영속성 컨텍스트에 들어감

    // SEQUENCE : 시퀀스, @SequenceGenerator 로 테이블 마다 각 시퀀스 사용 가능
    // persist 하기 일보직전에 시퀀스 넘버 가져와서 DB에 넣고 영컨에 저장
    // allocationSize : 한번에 메모리에 시퀀스 가져다 놓고 사용

    // TABLE : 키 생성 전용 테이블 만들어서 시퀀스 흉내, 성능 안 좋음
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name : 필드와 매핑할 테이블의 컬럼 이름
    // insertable, updatable : 등록 및 변경 가능 여부, 기본값 TRUE
    // nullable, false 시 not null 제약 조건
    // unique : 유니크 제약조건 랜덤 이름 생성으로 잘 안 씀
    // 대신 @Entity 적는 곳에 @Table(uniqueConstraints) 사용
    // length
    // columnDefinition = 설정 직접 입력
    @Column(name = "name")
    private String username;



    private int age;

    // DB에는 ENUM 타입이 없으므로
    // @Enumerated 사용
    // EnumType.ORDINAL : enum 순서를 데이터베이스에 저장(사용 X)
    // EnumType.String : enum 이름을 데이터베이스에 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 문자 타입이면 CLOB, 나머지는 BLOB으로 알아서 매핑
    // 지정할 수 있는 속성 없음
    @Lob
    private String description;

    // 메모리 전용으로 쓸 때
    @Transient
    private String temp;

    public Member() {

    }
}
