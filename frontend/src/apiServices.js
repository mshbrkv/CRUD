import httpCommon from "./http-common";


const getAllParticipants = (page) => {
    return httpCommon.get(`/participants?page=${page}`);
}


const getAllParticipantsBySearch = (query, page) => {
    return httpCommon.get(`/participants?query=${query}&page=${page}`);
}

const createParticipants = data => {
    return httpCommon.post("/participants", data);
}

const getParticipantsById = id => {
    return httpCommon.get(`/participants/${id}`);
}

const updateParticipants = (data, id) => {
    return httpCommon.put(`/participants/${id}`, data);
}

const deleteParticipants = id => {
    return httpCommon.delete(`/participants/${id}`)
}

export default {
    getAllParticipants,
    getAllParticipantsBySearch,
    createParticipants,
    getParticipantsById,
    updateParticipants,
    deleteParticipants
};