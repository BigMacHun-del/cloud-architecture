# cloud-architecture
클라우드 아키텍쳐 과제 레포지토리




## Lv_0.  AWS Budget 설정
![img.png](image/Budget.png)


## Lv_1. VPC, EC2
- VPC
![img.png](image/VPC.png)


- EC2
![img_1.png](image/EC2.png)


- 퍼블릭 ID 주소
3.36.105.181


- 배포 및 검증
![img.png](image/IP배포.png)

## Lv_2 RDS
- Parameter store
![img.png](image/Parameter_store.png)

  
- 인바운드 규칙
![img.png](image/인바운드.png)


- 로컬 연결 테스트

3306 포트의 보안 그룹이 SG->SG로 설정 되어 있기 때문에 EC2를 통해서 접근 함.
![img_1.png](image/RDS_로컬연결테스트.png)
![img.png](image/RDS_로컬연결테스트2.png)