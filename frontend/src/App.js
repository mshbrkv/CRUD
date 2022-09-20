import {BrowserRouter, Route, Routes} from "react-router-dom";
import ParticipantsList from "./Components/ParticipantsList";
import AddParticipant from "./Components/AddParticipant";
import NotFound from "./Components/notFound";
import EventList from "./Components/EventList";


function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/participants" element={<ParticipantsList/>}></Route>
                <Route path="/participants/add" element={<AddParticipant/>}></Route>
                <Route path="/participants/update/:id" element={<AddParticipant/>}></Route>
                <Route path="/participants/*" element={<NotFound/>}></Route>
                <Route path="/events" element={<EventList/>}></Route>

            </Routes>
        </BrowserRouter>
    )
}

export default App;