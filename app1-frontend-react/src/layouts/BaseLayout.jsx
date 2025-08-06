import Navbar from '../components/Navbar';

const BaseLayout = ({ children }) => {
    return (
        <>
            <header>
                <Navbar />
            </header>
            <main>
                {children}
            </main>
        </>
    )
};

export default BaseLayout;