import {useEffect, useState} from "react";
import {Link, useNavigate, useSearchParams} from "react-router-dom";
import apiServices from "../apiServices";

const EventList = () => {
    const [events, setEvents] = useState([]);
    const [availablePages, setAvailablePages] = useState();
    const navigate = useNavigate();
    let allPages = [];
    const [searchParams] = useSearchParams()
    const page = searchParams.get('page');


    const init = (page) => {
        apiServices.getAllEvents(page - 1)
            .then(response => {
                setEvents(response.data.content);
                setAvailablePages(response.data.totalPages);
            })
    }

    allPages = Array.from({length: availablePages}, (x = 1, i) => i + x);

    useEffect(() => {
        page ? init(page) : init(1);
    }, []);


    const deleteEvent = (id) => {
        apiServices.deleteParticipants(id)
            .then(() => {
                navigate('/events', {replace: true});
                init(1);
            })
    }


    return (
        <div>
            <Link to="/events/add">Add Event</Link>
            <br/>
            <br/>
            {/*<table>*/}
            {/*    <thead>*/}
            {/*    <tr>*/}
            {/*        <th>ID</th>*/}
            {/*        <th>Event`s name</th>*/}
            {/*        <th>Start time</th>*/}
            {/*        <th>in play</th>*/}
            {/*        <th>Participants</th>*/}
            {/*        <th>Market`s ID</th>*/}
            {/*    </tr>*/}
            {/*    </thead>*/}
            {/*    <tbody>*/}
            {/*    {*/}
            {/*        events.map(event=>(*/}
            {/*            <tr key={event.id}>*/}
            {/*                <td>{event.id}</td>*/}
            {/*                <td>{event.name}</td>*/}
            {/*                <td>{event.startTime}</td>*/}
            {/*                <td>{event.inPlay}</td>*/}
            {/*                <td>{event.participants}</td>*/}
            {/*                <td>{event.marketsId}</td>*/}
            {/*                <td>*/}
            {/*                    <Link to={`/events/update/${event.id}`}>Update</Link>*/}
            {/*                    <button onClick={(e) => {*/}
            {/*                        e.preventDefault();*/}
            {/*                        deleteEvent(event.id);*/}
            {/*                    }}>*/}
            {/*                        Delete*/}
            {/*                    </button>*/}
            {/*                </td>*/}
            {/*            </tr>*/}
            {/*        ))*/}
            {/*    }*/}
            {/*    </tbody>*/}
            {/*</table>*/}


            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Event`s name</th>
                    <th>Start time</th>
                    <th>in play</th>
                    <th>Participants</th>
                    <th>Market`s ID</th>
                </tr>
                </thead>
                <tbody>
                {
                    events.map(event => (
                        <tr key={event.id}>
                            <td>{event.id}</td>
                            <td>{event.name}</td>
                            <td>{event.startTime}</td>
                            <td>{event.inPlay}</td>
                            <td>{event.participants}</td>
                            {/*<td>*/}
                            {/*    <Link to={`/participants/update/${participant.id}`}>Update</Link>*/}
                            {/*    <button onClick={(e) => {*/}
                            {/*        e.preventDefault();*/}
                            {/*        deleteParticipant(participant.id);*/}
                            {/*    }}>*/}
                            {/*        Delete*/}
                            {/*    </button>*/}
                            {/*</td>*/}
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
                                    apiServices.getAllEvents(page - 1).then(response => {
                                        setEvents(response.data.content);
                                    })
                                    navigate(`/events?page=${page}`, {replace: true});
                                }}/>
                            )
                        )
                    }
                </form>
            </div>
        </div>
    )

}

export default EventList;