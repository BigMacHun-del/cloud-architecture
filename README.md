# cloud-architecture
작성자 : 김대훈




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


## Lv_3 S3
- 발급 받은 URL /  만료 시간: 2026-02-09 11:30:00
<details>
<summary>URL</summary>
<div markdown="1">

https://camp-health-rmsb6752-files.s3.ap-northeast-2.amazonaws.com/uploads/af222ca5-0f98-4992-81c7-9e6adbe1166b_%EB%B0%B0%ED%8F%AC%20%EC%82%AC%EC%A7%84.png?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEBMaDmFwLW5vcnRoZWFzdC0yIkgwRgIhAINBGqsgbvGBwJ2UFFlh1k8NTQCVQNLvUDMH36D9IudMAiEAsfrCYOA4sBYQ7Ggk4WDpoDQJE%2Fki%2BNi3N7J4oRKkJDUq0gUI3P%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgwwNjkzMDE1NzEwNTAiDPK9rDj%2BYS7w%2BPxovyqmBb0MAmpxhtaWAQ7qdKFiHhnkLVzh8N06lY7rlqnT4IlFnx60F3kkdjiHVzcfE7YXKdQUv0LoJts9KIDDvnBkuVjpHLO7U%2FNNXmzdPdNYj0RUuBoOhp1z5dUzE%2BvnswFb0hoO0L5j3dAht3lVRy61DYw1K8hVgd5llzPRfq0EuOqJtf5Jk6PwI1jyTtXOMiQpahllYwuWXtHnmSJRIV0oDQwQqMtIgshyV8DUG8Nqur%2BFcZXDsEzyN57uh%2BlsZ8eKCowDOnS4J2Q68CJ%2BvcV7gfO%2BQMx3ogxC7CQDC0E1XcRk3rtoH9ppQ%2F5oXcdfCh6qN7Eap68d8OPqHxIeeYBchhBtyUQ3ALjnXVOtiiQZWzmLpHDLF4dhvFEHS5EGwXAlTpsJXVh%2FGzex6MeNnmi4TDtsamN1Jokgf1ZyEUydr2nOAY2eKHDKUWR4UiINI4Sw3egSoyK7EL%2BjKjptLDi0ho0NZSfQmvmovFGjxG9K4nm%2BoDGXDXR4xIXSHZye9LS8g540j0EBTZ00akK9igKxv24vpsnKsOdWNYQhDnjO7cRF0ZwZiO%2FiD6mQZzBaSl4XKMiP00J5mm3T5cDeDvdvkSuZen9NfAfKj4NeYFy6o0Yz9iCItChfDM7EWHRDhNKAD%2FfDOQjKmTMWum9i%2FmgX7IYyLrTbIVHCNwn961w1AneDBK9NGid1sdfDjWmjYaWnoWCXKlG87IHQZ%2FpNEonPq9%2Bh5Lmrv6IrfXhb2uieyaujo9rofmDfwnasxJpmbJB12zM8My%2F%2BdrXKdreBO8qVMRFeVs%2B1nov3rcjzI6%2F69xa%2FdkRPfRbKDdETSZA7bV4nPYVVoJvsQgPm89xz08%2BOunc1Dgs%2Bm1h01nv5knFt4KeRIUAjgOuZYuwPsnyGK6GHkwSdEa2u5jD%2BnoDMBjqwAaleCq4sq6Do%2Fq7hpIwYeQrnmXSGRQ6qdNi1%2B5hqLiEMuy%2F20pj55OrM1HXf5%2BKoRaCEbiHktUhBjscbWQg%2FdZ3VFj%2B2OQi%2FgFX34qTqnZCCvP2D6DlptwMjCF1eLOc6YqZ%2Fs9TBF6jxqFXbf93lGuySvfir3BMRAjD%2FK8JpfZ0agjHg0CZxQFt57LyqWGVy74nGdnW%2FIlKT34apeWQe6KAR4b627M1vZL9ehB1I6lt%2F&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20260202T024813Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIARAIVSBXVIGOY5YQO%2F20260202%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=bef0485665ae78438b290e4493871e573c35b9d53b30d7abb09a492f8cbb51f3

</div>
</details>



- 부가 설명
![img.png](image/database_list.png)
데이터 베이스에 이미지 키와 url을 저장하였음.


- EC2 IAM 역할
![img_1.png](image/ec2_s3_role.png)
Access Key를 코드에 넣지 않고, S3 접근 권한이 있는 IAM Role을 생성해
  EC2에 연결하여 사용하였음.