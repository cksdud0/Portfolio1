// ⭐ URL에서 id 가져오기
function getArticleIdFromUrl() {
  const params = new URLSearchParams(window.location.search);
  return params.get("id"); // id 값 반환
}

// ⭐ JSON 데이터 불러오기 및 해당 기사 표시
async function fetchArticleData() {
  const articleId = getArticleIdFromUrl(); // 현재 URL에서 id 가져오기
  if (!articleId) {
    console.error("기사 id가 없습니다.");
    return;
  }

  try {
    const response = await fetch("js/articlesData.json"); // JSON 데이터 불러오기
    const data = await response.json();
    
    // id 값이 일치하는 기사 찾기
    const article = data.articles.find(item => item.id == articleId);
    
    if (!article) {
      console.error("해당 id의 기사가 없습니다.");
      return;
    }

    // ⭐ 기사 내용 표시 (기존 HTML 구조에 내용 추가)
    const articleTitle = document.querySelector('.article-title');
    const articleDescription = document.querySelector('.article-description');
    const photoContainer = document.querySelector('.photo');
    const articleDate = document.querySelector('.article-date');
    const viewCount = document.querySelector('.view-count');
    const editButton = document.querySelector('.edit-button');
    const deleteButton = document.querySelector('.delete-button');

    // 제목과 설명 추가
    articleTitle.textContent = article.title;
    articleDescription.textContent = article.description;

    // 날짜 추가 (JSP에서 조회수를 처리하므로, 날짜만 JSON에서 가져오기)
    articleDate.textContent = `작성일: ${article.date}`; // 기사 날짜 (예시로 "2025-04-01" 형식)

    // 썸네일 이미지 추가
    const imgElement = document.createElement('img');
    imgElement.src = article.thumbnail_url;
    imgElement.alt = '기사 이미지';
    photoContainer.appendChild(imgElement);

    // 수정 버튼 클릭 이벤트
    editButton.addEventListener("click", () => {
      alert("수정 기능은 아직 구현되지 않았습니다.");
      // 수정 기능은 JSP로 구현 예정
    });

    // 삭제 버튼 클릭 이벤트
    deleteButton.addEventListener("click", () => {
      alert("삭제 기능은 아직 구현되지 않았습니다.");
      // 삭제 기능은 JSP로 구현 예정
    });

  } catch (error) {
    console.error("기사 데이터를 불러오는 데 실패했습니다:", error);
  }
}

// ⭐ 실행
fetchArticleData();