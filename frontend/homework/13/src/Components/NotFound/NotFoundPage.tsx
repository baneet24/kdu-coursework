import { Link } from "react-router-dom";
import NotFoundImage from '../../images/404_image.jpg';

export function NotFoundPage(){
    return (
        <div>
            <p>Return to <Link to = "/">Home</Link></p>
             <img src={NotFoundImage} alt="Page Not Found.." style={{ width: '100%', maxWidth: '600px' } }/>
        </div>
    )
}