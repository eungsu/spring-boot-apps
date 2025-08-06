import BaseLayout from './BaseLayout';

const MainContentLayout = ({ title, children }) => {
    return (
        <BaseLayout>
            <div className="container mt-4">
                <div className="row mb-3">
                    <div className="col-12">
                        <div className="card">
                            <div className="card-header">
                                {title}
                            </div>
                            <div className="card-body">
                                {children}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </BaseLayout>
    )
};

export default MainContentLayout;