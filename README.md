# 개인 프로젝트 : Spring-Memo
***
## 📕 Tech Stacks
***
<div align="left">
<h3>⚙ Tools</h3>
<img src="https://img.shields.io/badge/intelliJ-f80000?style=flat&logo=IntelliJ IDEA&logoColor=black">
<img src="https://img.shields.io/badge/notion-ffffff?style=flat&logo=notion&logoColor=black">
<img src="https://img.shields.io/badge/github-000000?style=flat&logo=github&logoColor=#181717">
<img src="https://img.shields.io/badge/postman-e34f26?style=flat&logo=postman&logoColor=white">
<br>
<h3> 💻 Platform & Languages </h3>
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white">
<img src="https://img.shields.io/badge/CSS3-1572b6?style=flat&logo=CSS3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/jQuery-0569EF?style=flat&logo=jquery&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6db33f?style=flat&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/SpringBoot-6db33f?style=flat&logo=springBoot&logoColor=white">
<img src="https://img.shields.io/badge/BootStrap-7952B3?style=flat&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/MySql-4479a1?style=flat&logo=mysql&logoColor=white">
</div>

***

## 프로젝트 정리
<img src="https://img.shields.io/badge/-FFFFFF?style=flat&logo=notion&logoColor=black">
<a href="https://cm97.notion.site/Memo-Project-Spring-3a1f55d0c68e42d9bc0de7f3282a3e9e?pvs=4">게시글 프로젝트(Memo)</a>

***

## 2023/07/14 - 게시글, 댓글 좋아요 & AOP 예외처리 추가
### API 명세서 
https://app.gitbook.com/o/65TldPo6hrzFO0fO765W/s/w0bcaUUhyTjUHYfmvN8O/reference/api-reference

### ERD
![ERD](https://file.notion.so/f/s/6b933a08-6698-49c8-a142-f9ac42f3ba54/Untitled.png?id=1efe2928-af80-497f-a01e-5da8cc32ef22&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=ZXpNEuRYU8aglUCKaDDGh-ESA8JlFkNb4TWdIrJyDQ4&downloadName=Untitled.png)

### USECASE
![USECASE](https://file.notion.so/f/s/f559e990-ecbb-4db9-8a2e-967157343a1c/Untitled.png?id=bdb53ba9-0147-4faf-973b-98cc4a4401b8&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=r4T8MeO8rUX-Y-OMyfaz6aPdYskCnzchsRsMAkdpz0I&downloadName=Untitled.png)

### DB 설계
|      설계       | 결과                                                                                                                                                                                                                                                                                                       |
|:-------------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|     User      | ![user](https://file.notion.so/f/s/68b1e3b8-b522-4703-a527-2968ca18b569/Untitled.png?id=46459fee-00c9-4f12-8335-9d5a200acad5&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=n6eHOj_nuHU9HUobtJiPv5An54LwsTDT9T7iAvnrMQU&downloadName=Untitled.png) |
|     Memo      | ![Memo](https://file.notion.so/f/s/f49021cd-dc16-4a86-b866-df57700d6c49/Untitled.png?id=3e5b3519-604e-496a-a86e-32c3fb1f1932&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=QuxSiOCPlZPqNA-yfEyBKYT6sQ1ISp4vzZfxXR2Ub7w&downloadName=Untitled.png) |
|    Comment    | ![Comment](https://file.notion.so/f/s/6664e805-6422-451b-b560-e1a5ea8fc510/Untitled.png?id=57af0e30-9a9a-4190-86d6-c75beafae16f&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=L4DSja6apMkVcSQFkMMS1Q4qoxh7iFW4-HslyixQdl8&downloadName=Untitled.png)                                                                                                                                                                                                                                                                                             |
|   likeMemo    | ![likememo](https://file.notion.so/f/s/e626a81c-f89e-46cb-9a09-cc9fcdbbcae0/Untitled.png?id=35573a5b-0e86-4de9-8b51-212fb21b9b90&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=2OgqQzd-CABmGkZjppHbGW-QFNLCYgrzFfZ7bkrwcn4&downloadName=Untitled.png)                                                                                                                                                                                                                                                                                            |
|  likeComment  | ![likecomment](https://file.notion.so/f/s/5674321a-50cf-4225-a0e2-0a4fff228326/Untitled.png?id=fb57e60f-ca22-466c-99a2-f2a16dce6b3c&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=digUju5Wt5sTH2xQYLD5_ANBZMBdXZcWfaP9rMqzSus&downloadName=Untitled.png)                                                                                                                                                                                                                                                                                         |

***
### 결과화면
| 기능                             | 이미지     |
|--------------------------------|---------|
| 게시글 좋아요                        | ![1](https://file.notion.so/f/s/4131a871-98c1-4ea1-9741-21c603064cfe/Untitled.png?id=89706686-75b0-4b2b-8d8f-6f4113709f8d&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=BKg1-_DYt-1fuXJXsFIo9tgSIEskwBDNero8hPPheTg&downloadName=Untitled.png)  |
| 게시글 좋아요 취소                     | ![2](https://file.notion.so/f/s/f2cbc627-d296-4d7d-9dee-81d32d32ec27/Untitled.png?id=cc14cdaf-5437-47e5-a7a0-f6df6e3fa023&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=kp00cVeLQmvcboscWZBebbttzTP_l1ymj_YXYD_alLY&downloadName=Untitled.png)  |
| 댓글 좋아요                         | ![3](https://file.notion.so/f/s/07b15580-e492-4f0c-b602-825290941214/Untitled.png?id=fe9c87df-f28b-4b6d-8645-5dbcb1fe158d&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=TqTH8wvi44Jjw4wVNI4zE3ErLdAQ6bTDgDFSl2ojbAQ&downloadName=Untitled.png)  |
| 댓글 좋아요 취소                      | ![4](https://file.notion.so/f/s/33440f10-931d-483d-a5b8-b1366dd37684/Untitled.png?id=950a74bf-d1df-4bc5-b9de-acbb8c9af151&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=XQ7Qh_F5tKcxY-2SsmXWQePl4APvl-sbyPxBjQ-THfc&downloadName=Untitled.png)  |
| 게시글 조회                         | ![5](https://file.notion.so/f/s/c4e2abbd-f324-49e4-86a1-2e9b28a9b46e/Untitled.png?id=31ce8754-69b3-4f98-ace8-84980bd87d82&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=vF25xk0P0QbZtF4Zt4t5GeP0G9tzzk6hz7ugGZiiPLk&downloadName=Untitled.png)  |
| 댓글 조회                          | ![6](https://file.notion.so/f/s/be7ae1e7-7be0-4a5f-aa0d-8e64551e5ca8/Untitled.png?id=7ead4df3-92b5-419f-8f35-8e61c640aca2&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=1SrmZm-OHJvq-M599SI2YLcxPnoySxw2ayMaONhikXk&downloadName=Untitled.png)  |
| JWT 예외처리                       | ![7](https://file.notion.so/f/s/4881dca4-39ed-44b4-8000-30466732d654/Untitled.png?id=fef96253-e16c-4f01-9f87-d6e8ed0d2442&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=mXmL8H9t_r9TTZCnXZHQThOwTcuhw3KHD9cimx0tJL8&downloadName=Untitled.png)  |
| 회원을 찾을 수 없을 때                  | ![8](https://file.notion.so/f/s/feba344a-9664-4180-ace0-46963d5738e5/Untitled.png?id=fcf75c2a-136b-430d-8471-c45df5e7f26b&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=rmdA6gp6xMwH5zYzDmgfTRM6kA_b35muaJpJbc3fAcE&downloadName=Untitled.png)  |
| 중복된 유저명을 사용한 경우                | ![9](https://file.notion.so/f/s/427d24be-149f-4c61-81de-fc6d2e0fa921/Untitled.png?id=af383ec0-13ae-474d-a090-d34e9bc336d5&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=LwnH5uGNzwvImEXYWQXBOFKu7US7GXbboBXzyhBXmuU&downloadName=Untitled.png)  |
| 옳바르지 않은 username&password 사용 시 | ![10](https://file.notion.so/f/s/9edfba4b-5d6d-4dfe-8525-7ddc27f14280/Untitled.png?id=b2eff933-1630-47c5-a388-74fd81946f4a&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=Ma-vXr2PoE_wr2VAQkD3GLKqiymCwmRpHda_2CqoX7I&downloadName=Untitled.png) |
| 게시글의 소유자가 아닌 경우                | ![11](https://file.notion.so/f/s/b161c3b2-7863-4cfb-881c-a124a1b68b2e/Untitled.png?id=235c7718-1e1a-4f69-83f6-922138aad75d&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=bY0YOLGXSFAX2r8SKKyL5rHCCgVIfBgJRUMbd1aJpIo&downloadName=Untitled.png) |
| 댓글의 소유자가 아닌 경우                 | ![12](https://file.notion.so/f/s/e868b99e-c4fc-407f-a48d-859d4bbe9578/Untitled.png?id=6659e6a5-c8b5-414e-9f61-2575235f2dc0&table=block&spaceId=2cd4167f-7876-4a9d-88bd-04eba87468cf&expirationTimestamp=1689415200000&signature=ufJimkSAYwXoqdjTJhb6NvgEGZ9yfOUsM-s4Sqzteo0&downloadName=Untitled.png) |


***
## 2023/06/29 - 댓글 기능 + 코드 개선 ##
### API 명세서 ###
https://app.gitbook.com/o/65TldPo6hrzFO0fO765W/s/w0bcaUUhyTjUHYfmvN8O/reference/api-reference
### ERD ###
![ERD](https://user-images.githubusercontent.com/51190093/249614549-99e5f82e-5416-457c-9a43-466d1149f711.png)
### UseCase ###
![usercase](https://user-images.githubusercontent.com/51190093/249614541-dd31c242-23e5-4f52-9b72-da2baeaa92fe.PNG)
### 로그인 후,초기화면 ###
![1](https://user-images.githubusercontent.com/51190093/249617906-58fbc06e-270e-47d8-b5e9-f4503e7fb08b.PNG)

### 댓글 등록 ###
![2](https://user-images.githubusercontent.com/51190093/249617906-58fbc06e-270e-47d8-b5e9-f4503e7fb08b.PNG)
![3](https://user-images.githubusercontent.com/51190093/249617914-fb952352-839d-43d7-b1ba-54e4f4de76cb.PNG)

### 댓글 수정 ###
![4](https://user-images.githubusercontent.com/51190093/249617916-6bbdc0c9-df48-4d46-ba57-2aa7b446deae.PNG)
![5](https://user-images.githubusercontent.com/51190093/249617921-65179a00-bfb5-4d77-a541-47bf92b7b926.PNG)

### 댓글 삭제 ###
#### 쓰레기통 버튼 클릭 시, 현재 접속한 Username과 Comment작성 username을 비교하여 삭제 여부를 결정 ####

### Cascade.ALL & orphanRemoval=true ###
#### 부모 엔티티와 자식 엔티티의 생명주기를 동일하게 설정 ####
![6](https://user-images.githubusercontent.com/51190093/249617923-330a0536-57d1-4649-bf48-0f9740d21b95.PNG)
#### 부모 엔티티 삭제 시, 자식 엔티티 모두 삭제되는 것을 볼 수 있다. ####
![7](https://user-images.githubusercontent.com/51190093/249617926-345cc88a-0067-4841-900b-7d1ea50a0872.PNG)

---

## 2023/06/22 - 회원가입 & 로그인 구현 + 코드 개선 ##
### API 명세 ###
![API명세](https://user-images.githubusercontent.com/51190093/247959263-da2dbe54-8c6d-4d01-9860-9c1d55ff7a24.PNG)
### USECASE ###
![USECASE](https://user-images.githubusercontent.com/51190093/247959278-16b4bd5d-e941-4935-b4e1-4a3cb2a5f52f.PNG)
### 회원가입 ###
![회원가입](https://user-images.githubusercontent.com/51190093/247956790-622b132e-537b-414d-8f83-31aa5d32e87d.PNG)
![회원등록](https://user-images.githubusercontent.com/51190093/247956869-f5227756-4f22-4dbf-bba7-522f27ebe6b9.PNG)
![회원중복실패](https://user-images.githubusercontent.com/51190093/247956881-1565b710-b54a-488e-8933-67540663b1e6.PNG)
![회원중복실패2](https://user-images.githubusercontent.com/51190093/247956911-66dab81d-84a8-4a89-88df-d162f45b812b.PNG)

### 로그인 ###
![로그인](https://user-images.githubusercontent.com/51190093/247956930-dbb8b869-0ed2-4365-9bc7-ddb887ef213c.PNG)
![jwt토큰생성](https://user-images.githubusercontent.com/51190093/247959280-a8297395-dcc7-4993-99ef-330061e323f6.PNG)

### 메모 작성 ###
![메모작성](https://user-images.githubusercontent.com/51190093/247956975-ce3578dd-c906-4899-9db7-1cf31dce1edc.PNG)
![메모작성결과](https://user-images.githubusercontent.com/51190093/247956984-1573755c-3102-47c0-b2a8-7d1374eb738e.PNG)

### 상세 메모 조회 ###
![메모조회](https://user-images.githubusercontent.com/51190093/247957002-7acf330d-aff2-4c99-b264-ba77bd001de0.PNG)

### 메모 수정 ###
#### JWT 토큰에 저장된 USERNAME을 기준으로 메모를 수정할 권한이 주어진다. ####
![메모수정](https://user-images.githubusercontent.com/51190093/247957022-f96604e8-cd49-4d7f-a242-39bf09b8e05f.PNG)
![메모수정완료](https://user-images.githubusercontent.com/51190093/247957193-a57a3ced-fd75-44f4-bb57-9f7273433269.PNG)
![수정실패](https://user-images.githubusercontent.com/51190093/247957234-f920a6c4-8742-4779-b32e-8f5154396bf8.PNG)

### 메모 삭제 ###
#### JWT 토큰에 저장된 USERNAME을 기준으로 메모의 삭제 권한이 주어진다. ####
![메모삭제성공](https://user-images.githubusercontent.com/51190093/247957286-346cb719-8cd2-4e10-95f0-edbf092c456e.PNG)
![메모삭제](https://user-images.githubusercontent.com/51190093/247957300-43e6f662-ba44-4d0f-9c52-39f1e7e89f44.PNG)
![메모삭제실패](https://user-images.githubusercontent.com/51190093/247957315-81fe4df7-224c-4e5d-98de-e5d1e4781b94.PNG)

---
## 2023/06/15 - CRUD 기능 ##
### API 명세 ###
![API명세](https://user-images.githubusercontent.com/51190093/246301888-2a8dd777-8aa9-4446-9730-eaff69be1a8c.PNG)

### 메인 페이지(게시글 출력) & 게시글 추가 ###
![main&addMemo](https://user-images.githubusercontent.com/51190093/246298144-25c72a1d-f987-4710-b96f-1e45b9c42a33.PNG)
![main check](https://user-images.githubusercontent.com/51190093/246298153-6a491608-0c4b-4980-86af-e4b5f150703e.PNG)

### 게시글 수정 ###
#### 게시글 수정 시, 제목, 내용, 비밀번호를 입력받고, 입력받은 내용을 기반으로 Controller에서 RequestDto로 객체 변환하여 Service에서 패스워드 검증을 수행한다.
![updateMemo](https://user-images.githubusercontent.com/51190093/246298157-9c88a341-00c5-4c03-bf0f-a42678498b28.PNG)
![updateMemo2](https://user-images.githubusercontent.com/51190093/246298163-cb81fabd-7969-474a-99af-db780748c537.PNG)
![updateMemo3](https://user-images.githubusercontent.com/51190093/246298165-ebd386cf-a490-4c12-9cc8-00a6f1e70354.PNG)

### 게시글 삭제 ###
#### 게시글 삭제 시, 비밀번호를 요구하고, 비밀번호를 입력하지 않으면, Alert를 호출하고, Server 내의 Service layer에서 패스워드 비교 검증을 진행하여 맞으면 해당 객체를 반환하고, 틀렸다면, IllegalArgumentException()을 반환하여 예외를 반환시킨다.  
![deleteMemo](https://user-images.githubusercontent.com/51190093/246298171-21766f55-c95f-4fe8-a74f-161741536774.PNG)
![deleteMemo2](https://user-images.githubusercontent.com/51190093/246298172-768c37da-808b-487b-8467-861499d5d25d.PNG)