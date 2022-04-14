# 99List
항해99 6주차 미니프로젝트 4조

🧑🏻‍💻 제작 기간 및 팀원 소개
기간 : 2022 - 04 - 08 ~ 2022 - 04 - 14   

Backend(Spring)

    송은혜 : 시큐리티&jwt / 사용자 정보 조회API / 회원 탈퇴API
    박만수 : TO-DO 삭제,수정 API / 체크박스 (TO-DO완료체크) API
    김호빈 : TO-DO 전체 조회, 등록 API 

Frontend(React) : 

    최지은
    이경태
https://github.com/hanghaeMini2-FrontEnd/TodoList

<br>

🛠 사용 기술

- Spring boot
- JPA
- Spring security
- H2
- MySql
- AWS
- GitHub

<br>

# 99List 는 ?

해야할 일들을 TO-DO List로 등록하고 관리할 수 있게하는 간단한 to-do list 서비스.
타 유저와 공유 없이, 로그인한 본인의 목록만 확인하는 프라이빗한 투두리스트입니다!



# 다가왔던 문제들과 해결방안
- 그래이들에서 build 후 서버에 올려서 build 파일을 실행할때,  jwt class를 찾지 못하는 문제.
    
    build.gradle 에서 설정을 compileOnly → implementation 으로 변경하고 나서 
    
    해결 할 수 있었다.
    

- 투두리스트 조회시, 스택오버 플로우 발생
    
    Plan과 User 가 양방향 Mapping 이 되어 있어서,
    
    Plan을 List 형식으로 뿌려 줄 때, Plan에 있는 User가 다시 참조되면서 일어났던 문제
    
    임시로 Plan의 User를 null로 바꿔 준 뒤 Front에 넘겨서 해결.
    

- 유저정보 조회 API 실행되지 않음
    
    UserDetails 를 활용해서 유저정보를 꺼내려고 함. GETMapping을 사용했는데 작동되지 않았고, POSTMapping으로 바꾸니 실행됨.
    

- Front 단에 error 메세지 넘기기
    
    Back에서는 *`throw new* IllegalArgumentException("비밀번호는 필수 입력값입니다.");` 으로 하게 되면 로그가 잘 뜨는데 이것을 프론트에 넘기기 위한 방법을 몰랐다. 
    
    이를 해결하기 위해 AOP 에 대해 공부하고 *`@RestControllerAdvice`*를 활용해 http 상태코드와 message를 함께 넘겨주어 프론트 단에 조금이나마 에러에 대한 설명을 넘겨줄 수 있었다.
    
    ```
    @ExceptionHandler(IllegalArgumentException.class)
    publicResponseEntityillegalExHandle(IllegalArgumentExceptione){
        log.error(e.getMessage());
    return newResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    ```

Notion Postman
https://www.notion.so/99-A-4-8c535480c3034fdab460445e05542d66
