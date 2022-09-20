import httpCommon from "./http-common";


const getAllParticipants = (page) => {
    return httpCommon.get(`/participants?page=${page}`);
}

const getAllEvents = (page) => {
    return httpCommon.get(`/events?page=${page}`);
}


const getAllParticipantsBySearch = (query, page) => {
    return httpCommon.get(`/participants?query=${query}&page=${page}`);
}

const createParticipants = data => {
    return httpCommon.post("/participants", data);
}
const createEvent = data => {
    return httpCommon.post("/events", data);
}

const getParticipantsById = id => {
    return httpCommon.get(`/participants/${id}`);
}

const getEventById = id => {
    return httpCommon.get(`/events/${id}`);
}
const getEventInPlay = (page) => {
    return httpCommon.get(`/events?page=${page}`);
}

const updateParticipants = (data, id) => {
    return httpCommon.put(`/participants/${id}`, data);
}
const updateEvent = (data, id) => {
    return httpCommon.put(`/events/${id}`, data);
}

const deleteParticipants = id => {
    return httpCommon.delete(`/participants/${id}`)
}

const deleteEvents = id => {
    return httpCommon.delete(`/events/${id}`)
}
export default {
    getAllParticipants,
    getAllParticipantsBySearch,
    createParticipants,
    createEvent,
    getParticipantsById,
    updateParticipants,
    updateEvent,
    deleteParticipants,
    getAllEvents,
    getEventById,
    getEventInPlay,
    deleteEvents
};