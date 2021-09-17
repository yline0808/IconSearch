# ImageList

## 프로젝트 계획
> 저작권이 없는 무료 이미지 사이트에서 이미지를 Jsoup으로 크롤링을 하여 보여주는 앱을 개발하려고 합니다. 크롤링 시 코루틴을 사용할 계획이고, 이미지 개수가 많으므로 Glide 라이브러리를 사용하여 이미지를 recyclerView에서 볼 수 있도록 할 예정입니다.
+ 사이트
    + URL : https://pixabay.com/
    
+ 개발 순서
    1. jsoup으로 이미지 정보 크롤링 후 출력 (Coroutine 사용)
    2. 쿼리파라미터 변경하는 코드 추가
    3. Glide를 사용하여 불러온 이미지 출력
    4. recyclerview와 StaggeredGrid를 사용하여 이미지출력 고도화 (페이지 무한스크롤 기능 추가)

+ 사용기술
    + Jsoup
    + Coroutine
    + Glide
