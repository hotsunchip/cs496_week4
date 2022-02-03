# 몰입캠프 4주차 - 굿투밋
## 팀원 소개
- [김덕현](https://github.com/Kim-Deokhyeon1) : 프론트엔드
- [윤태양](https://github.com/hotsunchip) : 프론트엔드
- [이우진](https://github.com/woojinnn) : [백엔드](https://github.com/woojinnn/madcamp_pj4_backend) 및 프론트엔드


## 프로젝트 소개
- !약속에 필요한 모든 기능을 하나의 앱으로!
- 이 앱은 약속 전, 약속이 진행되는 중, 약속이 끝난 후의 각 상황에서 유용하게 쓸 수 있는 기능들을 만드는 것을 목표로 한다.
- 아래는 현재까지 구현 된 기능들에 대한 간략한 소개이며, Android Studio와 Node.JS를 사용하였다.

## 사용자 시나리오 흐름에 따른 기능 설명
### 1. 회원가입 및 로그인

| <img src="https://user-images.githubusercontent.com/93712189/151428395-d18b3b24-8b5f-41f0-b53f-7ce31fe1d89d.gif" width="360"> |
|:---:|
|회원가입 및 내 위치 수정하기|

- 로그인 및 회원가입은 서버에 아이디와 비밀번호를 POST하고, 이것이 유효할 경우 사용자를 식별할 수 있는 토큰을 받아오는 방식으로 구현하였다.
- 처음 회원가입을 하면, 내 위치에 해당하는 주소를 회원정보에 저장하는 단계로 넘어가도록 하였다.
- 내 위치 수정하기 기능은 추후 구현할 예정인 정시에 약속장소에 도착하기 위한 출발시간을 계산할 때 사용할 예정이다.

### 2. 약속

| <img src="https://user-images.githubusercontent.com/93712189/151428696-4ec36ccf-a0f2-4c40-9a7a-de45b4dc6b1b.gif" width="360"> | <img src="https://user-images.githubusercontent.com/93712189/151428705-dbd5c509-7f4d-4939-a880-519d75b5c555.gif" width="360"> | <img src="https://user-images.githubusercontent.com/93712189/151428716-7d6d43a9-0ec3-4b8c-ae5c-5bee18bc8305.gif" width="360"> | <img src="https://user-images.githubusercontent.com/93712189/151428728-a4eb441f-b3ea-465e-bb12-24225ba51202.gif" width="360"> |
|:---:|:---:|:---:|:---:|
|핵심 FAB|새 약속 만들기|약속 수정하기|멤버 추가하기|

- 로그인 후 처음 보이는 화면에서 중앙하단의 버튼을 클릭하면 새 약속 만들기 탭으로 이동하는 버튼이 있다.
- 이 탭에서 약속 이름, 시작시간, 장소를 선택하면, 새로운 약속을 만들 수 있다.
- 약속이 생성된 후 보이는 창에서 위치 확인하기 버튼을 클릭하면, 약속장소의 주소를 이용해 위치를 구글맵에 보여준다.
- 멤버 추가하기 버튼을 클릭하면 전체 사용자의 리스트를 확인할 수 있고, 이 중 원하는 사람을 내 약속에 초대할 수 있다.

## 3. 티티

| <img src="https://user-images.githubusercontent.com/93712189/151430266-75339556-48a1-4c53-ac8a-c7bb2407d4dc.gif" width="360"> |
|:---:|
|티티 생성하기|

- 티티는 when2meet에서 착안한 기능으로, TimeTable의 약자로서 주최자가 약속시간의 후보로 가능한 날짜와 시간을 정하는 것으로 생성한다.
- 이후 초대코드를 통해 약속의 구성원들이 해당 약속의 티티에 접근할 수 있으며, 드래그를 통해 가능한 날짜 및 시간을 선택할 수 있다.
- 각 구성원은 실시간으로 가능하다고 표시된 시간대를 확인할 수 있으며, 많은 사람이 선택한 시각을 짙은 색으로 표시하였다.
