# Spring-Memo
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