/**
  main.jsx
  - 리액트 애플리케이션의 진입점이 되는 자바스크립트 파일이다.
  - 리액트 앱을 브라우저에 렌더링하고 필요한 외부 리소스를 포함하고 있다.
*/

/*
  리액트 및 루트 API
  - StrictMode는 React의 개발 모드에서만 동작하는 디버깅 도구다.
  - 앱의 잠재적인 문제(예: deprecated API 사용, 부적절한 사이드 이펙트 등)를 감지한다.
  - createRoot는 React 18부터 도입된 새로운 루트 API다.
  - createRoot()는 document.getElementById('root') DOM 요소에 React를 연결한다.
*/
import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';

/*
  앱의 최상위 컴포넌트 불러오기
  - 리액트 앱의 최상위 컴포넌트인 App를 가져온다.
  - App 컴포넌트 안에 나머지 모든 UI 컴포넌트가 포함되어 있다.
*/
import App from './App.jsx';

/*
  외부 스타일 및 JS 리소스 로드
  - Bootstrap CSSS를 포함한다.
  - Bootstrap Icons플 포함한다. 다양한 아이콘을 사용할 수 있다.
  - Bootstrap의 JavaScript를 로딩한다.(모달, 드롭다운, 토글 등을 지원한다.)
*/
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

/*
  리액트 앱을 DOM에 렌더링한다
  - document.getElementById('root')는 index.html의 <div id="root"></div>를 가져온다.
  - createRoot(...)는 해당 DOM요소에 리액트 렌더링 엔진을 연결한다.
  - createRoot(...).render(...)는 APP 컴포넌트를 브라우저에 출력한다.
  - <StrictMode>...</StrictMode>는  앱의 안정성과 유지보수성을 높이기 위해 앱의 잠재적인 문제를 감지하고 알려준다.
*/
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
