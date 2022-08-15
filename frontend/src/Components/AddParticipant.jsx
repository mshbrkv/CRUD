import {useEffect, useState} from "react";
import {Link, useParams, useNavigate} from "react-router-dom";
import apiServices from "../apiServices";

const AddParticipant = () => {
    const [name, setName] = useState('');
    const [sport, setSport] = useState('');
    const [country, setCountry] = useState('');
    const [externalId, setExternalId] = useState(4);

    const navigate = useNavigate();
    const {id} = useParams();


    const saveParticipant = (e) => {
        e.preventDefault();
        const participant = {id, name, sport, country, externalId};

        if (id) {
            apiServices.updateParticipants(participant, id)
                .then(response => {
                    console.log('Employee data updated successfully', response.data);
                    navigate("/participants", {replace: true})
                })
                .catch(error => {
                    console.log('Something went wrong', error)
                })
        } else {
            apiServices.createParticipants(participant)
                .then(response => {
                    console.log("employee added successfully", response.data);
                    navigate("/participants", {replace: true});
                })
                .catch(error => {
                    console.log('Something went wrong', error)
                })
        }
    }

    useEffect(() => {
        if (id) {
            apiServices.getParticipantsById(id)
                .then(participant => {
                    setName(participant.data.name);
                    setSport(participant.data.sport);
                    setCountry(participant.data.country);
                    setExternalId(participant.data.externalId);
                })
                .catch(error => {
                    console.log('Something went wrong', error)
                });
        }
    }, []);


    return (
        <div>
            <h3>Add new Participant</h3>
            <hr/>
            <form>
                <div>
                    <label>Name
                    <input type="text" id="name" value={name}
                           onChange={(e) => setName(e.target.value)}
                           placeholder="Enter name"/></label>
                </div>
                <div>
                    <label>Sport
                    <input type="text" id="sport" value={sport}
                           onChange={(e) => setSport(e.target.value)}
                           placeholder="Enter sport"/></label>
                </div>
                <div>
                    <label>Country
                    <input type="text" id="country" value={country}
                           onChange={(e) => setCountry(e.target.value)}
                           placeholder="Enter country"/></label>
                </div>
                <div><label>External ID
                    <input type="text" id="externalId" value={externalId}
                           onChange={(e) => setExternalId(Number(e.target.value))}
                           placeholder="Enter externalId"/></label>
                </div>
                <div>
                    <button onClick={(e) => saveParticipant(e)}>Save</button>
                </div>
            </form>
            <hr/>
            <Link to="/participants">Back to all participants</Link>
        </div>
    )

}

export default AddParticipant;