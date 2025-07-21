const Pagination = ( {paging, onPageChange }) => {
    if (!paging || paging.totalRows === 0) {
        return null;
    }

    const handleClick = (pageNo) => {
        if (pageNo !== paging.page) {
            onPageChange(pageNo);
        }
    }

    const pageNumbers = [];
    for (let i = paging.beginPage; i<= paging.endPage; i++) {
        pageNumbers.push(i);
    }

    return (
        <nav>
            <ul className="pagination justify-content-center">
                <li className={`page-item ${paging.first ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={() => handleClick(paging.prevPage)}>이전</button>
                </li>
                {pageNumbers.map((pageNo) => (
                    <li key={pageNo} className={`page-item ${pageNo === paging.page ? 'active' : ''}`}>
                        <button className="page-link" onClick={() => handleClick(pageNo)}>{pageNo}</button>
                    </li>
                ))}
                <li className={`page-item ${paging.last ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={() => handleClick(paging.nextPage)}>다음</button>
                </li>
            </ul>
        </nav>
    );

};

export default Pagination;