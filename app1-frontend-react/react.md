# 리액트
- **React**는 **사용자 인터페이스(User interface)** 를 만들기 위한 **JavaScript 라이브러리**다.
- React는 컴포넌트 기반의 선언형 UI 라이브러리이며, 상태에 따라 UI를 효율적으로 업데이트한다.

## 주요 특징
- 선언형 프로그래밍을 지원
  - 상태(state)가 변경되면 UI는 자동으로 업데이트된다.
- 컴포넌트 기반 구조
  - UI를 재사용 가능한 컴포넌트 단위로 나누어서 개발한다.
- Virtual DOM
  - React는 실제 DOM이 아닌 **Virtual DOM**에 변경사항을 먼저 적용하고, 변경된 부분만 실제 DOM에 반영한다.
- 단방향 데이터 흐름
  - React에서 데이터는 **부모 컴포넌트 -> 자식 컴포넌트** 방향으로 전달된다.
- React Hook 지원
  - `useState`, `useEffect`, `useContext` 등 다양한 Hook을 제공한다.
  - Hook은 함수형 컴포넌트에서 React의 상태관리, 생명주기, 기타 기능을 지원하는 특수한 함수다.
- JSX(JavaScript + XML 문법)
  - HTML과 유사한 문법을 JavaScript안에 작성할 수 있게 한다.
  - 개발자가 UI을 쉽게 구성할 수 있게 한다.

## 개발하기
### 인사말 출력하기
```jsx
// App.jsx 
const App = () => {
  return (
    <div>
      <h1>안녕하세요</h1>
    </div>
  );
};
export default App;
```

```jsx
// main.jsx
import { createRoot } from 'react-dom/client';
import App from './App.jsx';

createRoot(document.getElementById('root')).render(<App />);
```

### 데이터 표현하기
#### 변수값 렌더링
```jsx
// App.jsx
const App = () => {
  const name = "홍길동";
  return (
    <div>
      <h1>안녕하세요, {name}님!</h1>
    </div>
  );
}
```

#### 객체 데이터 렌더링
```jsx
// App.jsx
const App = () => {
  const user = { name:"홍길동", age:25};
  return (
    <div>
      <p>이름: {user.name}</p>
      <p>나이: {user.age}</p>
    </div>
  );
}
```

#### 배열 데이터 렌더링
```jsx
// App.jsx
const App = () => {
  const employees = [{no:1, name:"홍길동", dept:"영업팀"}, {no:2, name:"김유신", dept:"경리부"}];
  return (
    <div>
      <h1>직원 목록</h1>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>부서명</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((emp) => (
            <tr key={emp.no}>
              <td>{emp.no}</td>
              <td>{emp.name}</td>
              <td>{emp.detp}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
// 배열을 map() 함수로 순회하며 JSX 엘리먼트를 생성한다.
// 각 항목에는 고유한 key 속성을 부여한다.
```

#### 조건부 데이터 렌더링
```jsx
// App.jsx
const App = ({ isLogined }) => {
  return (
    <div>
      <ul>
        <li>홈</li>
        {isLogined && (
          <li>게시글</li>
        )}
        {isLogined ? (
          <li>로그아웃</li>
        ) : (
          <>
            <li>로그인</li>
            <li>회원가입</li>
          </>
        )}
      </ul>
    </div>
  )
}
// {isLogined && (isLogined가 true로 판정될 때 렌더링된다)}
// {isLogined ? (isLogined가 true로 판정될 때 렌더링된다) : (isLogined가 false로 판정될 때 렌더링된다)}
```
