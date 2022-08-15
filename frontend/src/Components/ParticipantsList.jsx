import {useEffect, useState} from "react";
import {Link, useNavigate, useSearchParams} from "react-router-dom";
import apiServices from "../apiServices";

const ParticipantsList = () => {
    const [participants, setParticipants] = useState([]);
    const [availablePages, setAvailablePages] = useState();
    const [query, setQuery] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const navigate = useNavigate();
    let allPages = [];
    const [searchParams] = useSearchParams()
    const page = searchParams.get('page')

    const init = (page) => {
        apiServices.getAllParticipants(page - 1)
            .then(response => {
                setParticipants(response.data.content);
                setAvailablePages(response.data.totalPages);
            })
    }

    allPages = Array.from({length: availablePages}, (x = 1, i) => i + x)

    useEffect(() => {
        page ? init(page) : init(1);

    }, []);

    const deleteParticipant = (id) => {

        apiServices.deleteParticipants(id)
            .then(() => {
                    navigate(`/participants?page=1`, {replace: true})
                    init(1);
            })
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        apiServices.getAllParticipantsBySearch(query, currentPage).then(r => {
            setParticipants(r.data.content);
            setAvailablePages(r.data.totalPages);
            if (r.data.content.length === 0) {
                navigate(`/participants/*`, {replace: true})
            } else {
                navigate(`/participants?query=${query}`, {replace: true})
            }
        });
    }

    return (
        <div>
            <h2>Participants1 list</h2>
            <hr/>
            <div>
                <Link to="/participants/add">Add Participant</Link>
                <br/>
                <br/>
                <form>
                    <input type="text" value={query} onChange={(e) => {
                        setQuery(e.target.value)
                    }}/>
                    <button type="submit" onClick={handleSubmit}>Search</button>
                </form>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Sport</th>
                        <th>Country</th>
                        <th>External ID</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        participants.map(participant => (
                            <tr key={participant.id}>
                                <td>{participant.id}</td>
                                <td>{participant.name}</td>
                                <td>{participant.sport}</td>
                                <td>{participant.country}</td>
                                <td>{participant.externalId}</td>
                                <td>
                                    <Link to={`/participants/update/${participant.id}`}>Update</Link>
                                    <button onClick={(e) => {
                                        e.preventDefault();
                                        deleteParticipant(participant.id);
                                        setQuery('');
                                    }}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
                <div>
                    <form>
                        {
                            allPages.map(page => (
                                <input key={page} type="button" value={page} onClick={(e) => {
                                    e.preventDefault();
                                    if (query !== "") {
                                        apiServices.getAllParticipantsBySearch(query, page - 1).then(response => {
                                            setParticipants(response.data.content)
                                        })
                                        navigate(`/participants?query=${query}&page=${page}`, {replace: true})
                                    } else {
                                        apiServices.getAllParticipants(page - 1).then(response => {
                                            setParticipants(response.data.content)
                                        })
                                        navigate(`/participants?page=${page}`, {replace: true});
                                    }
                                }}/>
                            ))
                        }
                    </form>

                </div>
            </div>
        </div>
    )
}

export default ParticipantsList;