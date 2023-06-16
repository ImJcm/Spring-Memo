# Spring-Memo
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