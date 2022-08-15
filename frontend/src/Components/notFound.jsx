import {Link} from "react-router-dom";

const NotFound=()=>{
    return(
        <div>
            <p>
                Nothing found.
            </p>
            <Link to="/participants">Back to all participants</Link>
        </div>


    )
}

export default NotFound;