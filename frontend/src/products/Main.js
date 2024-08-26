import Preview from "../components/common/Preview";
import Card from "../components/common/Card";
import SubTtitle from "../components/common/SubTitle";
import '../styles/sans.css';

const Main = () => {
    return (
        <div class="container px-4 px-lg-5">
            <Preview/>
            <SubTtitle/>
            <Card/>
        </div>
        
    );
};

export default Main;