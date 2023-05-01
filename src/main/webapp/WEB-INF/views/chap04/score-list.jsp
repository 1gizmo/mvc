<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        label {
            display: block;
        }

        .score-list>li {
            margin-bottom: 10px;
        }

        .score-list>li:first-child {
            font-size: 1.2em;
            color: blue;
            font-weight: 700;
            border-bottom: 1px solid skyblue;
        }

        .del-btn {
            width: 10px;
            height: 10px;
            background: red;
            color: #fff;
            border-radius: 5px;
            margin-left: 5px;
            text-decoration: none;
            font-size: 0.7em;
            padding: 6px;
        }

        .del-btn:hover {
            background: orangered;
        }

        section.score {
            padding: 200px 50px 100px;
            font-size: 1.5em;
        }

        .list-header {
            display: flex;
            justify-content: space-between;

            width: 50%;
        }
        .list-header .sort-link-group {
            display: flex;

        }
        .list-header .sort-link-group div {
            margin-right: 20px;
        }

    </style>
</head>

<body>

    <div class="wrap">

        <section class="score">
            <h1>시험 점수 등록</h1>
            <form action="/score/register" method="POST">
                <label>
                    # 이름: <input type="text" name="name">
                </label>
                <label>
                    # 국어: <input type="text" name="kor">
                </label>
                <label>
                    # 영어: <input type="text" name="eng">
                </label>
                <label>
                    # 수학: <input type="text" name="math">
                </label>
                <label>
                    <button type="submit">확인</button>
                    <button id="go-home" type="button">홈화면으로</button>
                </label>
            </form>

            <hr>

            <ul class="score-list">
                <li class="list-header">
                    <div class="count">총 학생 수: ${sList.size()}명</div>
                    <div class="sort-link-group">
                        <div><a href="/score/list?sort=num">학번순</a></div>
                        <div><a href="/score/list?sort=name">이름순</a></div>
                        <div><a href="/score/list?sort=avg">평균순</a></div>
                    </div>

                </li>

                <c:forEach var="s" items="${sList}">
                    <li>
                        # 학번: ${s.stuNum}, 이름: <a href="/score/detail?stuNum=${s.stuNum}">${s.maskingName}</a>, 
                        평균: ${s.average}점, 학점: ${s.grade}
                        <a class="del-btn" href="/score/remove?stuNum=${s.stuNum}">삭제</a>
                    </li>
                </c:forEach>
                
            </ul>

        </section>



    </div>

    <script>

        const $cardContainer = document.querySelector('.card-container');

        //================= 삭제버튼 스크립트 =================//
        const modal = document.getElementById('modal'); // 모달창 얻기
        const confirmDelete = document.getElementById('confirmDelete'); // 모달 삭제 확인버튼
        const cancelDelete = document.getElementById('cancelDelete'); // 모달 삭제 취소 버튼
    
        $cardContainer.addEventListener('click', e => {
            // 삭제 버튼을 눌렀다면~
            if (e.target.matches('.card-btn-group *')) {
                console.log('삭제버튼 클릭');
                modal.style.display = 'flex'; // 모달 창 띄움

                const $delBtn = e.target.closest('.del-btn');
                const deleteLocation = $delBtn.dataset.href;

                // 확인 버튼 이벤트
                confirmDelete.onclick = e => {
                    // 삭제 처리 로직
                    window.location.href = deleteLocation;

                    modal.style.display = 'none'; // 모달 창 닫기
                };


                // 취소 버튼 이벤트
                cancelDelete.onclick = e => {
                    modal.style.display = 'none'; // 모달 창 닫기
                };
            } else { // 삭제 버튼 제외한 부분은 글 상세조회 요청

                // section태그에 붙은 글번호 읽기
                const bno = e.target.closest('section.card').dataset.bno;
                // 요청 보내기
                window.location.href= '/board/detail?bno=' + bno;
            }
        });

        // 전역 이벤트로 모달창 닫기
        window.addEventListener('click', e => {
            if (e.target === modal) {
                modal.style.display = 'none';
            }
        });

        //========== 게시물 목록 스크립트 ============//

        function removeDown(e) {
            if (!e.target.matches('.card-container *')) return;
            const $targetCard = e.target.closest('.card-wrapper');
            $targetCard?.removeAttribute('id', 'card-down');
        }

        function removeHover(e) {
            if (!e.target.matches('.card-container *')) return;
            const $targetCard = e.target.closest('.card');
            $targetCard?.classList.remove('card-hover');

            const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
            $delBtn.style.opacity = '0';
        }

        

        $cardContainer.onmouseover = e => {

            if (!e.target.matches('.card-container *')) return;

            const $targetCard = e.target.closest('.card');
            $targetCard?.classList.add('card-hover');

            const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
            $delBtn.style.opacity = '1';
        }

        $cardContainer.onmousedown = e => {

            if (e.target.matches('.card-container .card-btn-group *')) return;

            const $targetCard = e.target.closest('.card-wrapper');
            $targetCard?.setAttribute('id', 'card-down');
        };

        $cardContainer.onmouseup = removeDown;

        $cardContainer.addEventListener('mouseout', removeDown);
        $cardContainer.addEventListener('mouseout', removeHover);

        // write button event
        document.querySelector('.add-btn').onclick = e => {
            window.location.href = '/board/write';
        };

        

    </script>

</body>

</html>