const carousel = document.querySelector(".carousel");
const newsList = document.querySelector(".news_list");
const prevButton = document.querySelector(".carousel-prev");
const nextButton = document.querySelector(".carousel-next");
let slideInterval;

// ⭐ 캐러셀 이동 함수
function moveCarousel(direction) {
    const images = carousel.children;
    const width = carousel.offsetWidth / 3;

    if (direction === 'next') {
        carousel.style.transition = "transform 0.5s ease-in-out";
        carousel.style.transform = `translateX(-${width}px)`;

        setTimeout(() => {
            carousel.appendChild(images[0]);
            carousel.style.transition = "none";
            carousel.style.transform = "translateX(0)";
        }, 500);
    } else if (direction === 'prev') {
        carousel.style.transition = "none";
        carousel.insertBefore(images[images.length - 1], images[0]);
        carousel.style.transform = `translateX(-${width}px)`;

        setTimeout(() => {
            carousel.style.transition = "transform 0.5s ease-in-out";
            carousel.style.transform = "translateX(0)";
        }, 10);
    }
}

// ⭐ 자동 슬라이드 기능
function autoSlide() {
    slideInterval = setInterval(() => moveCarousel('next'), 3000);
}

// ⭐ 마우스를 캐러셀에 올리면 자동 슬라이드 멈추기
carousel.addEventListener('mouseenter', () => clearInterval(slideInterval));

// ⭐ 마우스를 캐러셀에서 떼면 자동 슬라이드 재시작
carousel.addEventListener('mouseleave', autoSlide);

// ⭐ 화살표 버튼 이벤트 추가
prevButton.addEventListener("click", () => moveCarousel('prev'));
nextButton.addEventListener("click", () => moveCarousel('next'));

// ⭐ JSON 데이터 불러오기 및 동적 생성
async function fetchArticles() {
    try {
        const response = await fetch('js/articles.json');
        const data = await response.json();

        data.articles.slice(0, 5).forEach((article, index) => {
            const imgElement = document.createElement('img');
            imgElement.src = article.thumbnail_url;
            imgElement.alt = `이미지 ${index + 1}`;
            imgElement.style.cursor = "pointer";

            // ✅ 여기 수정: id 값을 넘겨서 이동하도록 변경
            imgElement.addEventListener("click", () => {
                window.location.href = `news.html?id=${article.id}`;
            });

            carousel.appendChild(imgElement);
        });

        data.articles.forEach((article, index) => {
            const newsItem = document.createElement('div');
            newsItem.classList.add('news_item');
            newsItem.style.cursor = "pointer";

            const img = document.createElement('img');
            img.src = article.thumbnail_url;
            img.alt = `기사 이미지 ${index + 1}`;

            const title = document.createElement('h3');
            title.textContent = article.title;

            const description = document.createElement('p');
            description.textContent = article.description;

            newsItem.appendChild(img);
            newsItem.appendChild(title);
            newsItem.appendChild(description);

            // ✅ 여기 수정: id 값을 넘겨서 이동하도록 변경
            newsItem.addEventListener("click", () => {
                window.location.href = `news.html?id=${article.id}`;
            });

            newsList.appendChild(newsItem);
        });

        autoSlide();

    } catch (error) {
        console.error("기사 데이터를 불러오는 데 실패했습니다:", error);
    }
}

// ⭐ 초기 실행
fetchArticles();