import request from "@/utils/request";

export  const getProblems = ( pageInfo ) =>{
    return request.post("/problem/getProblems",pageInfo);
}

export const getProblem = ( problemInfo ) =>{
    return request.post("/problem/getProblem",problemInfo)
}

export const addProblem = ( problemInfo ) =>{
    return request.post("/problem/addProblem",problemInfo)
}

export const deleteProblem = ( problemInfo ) =>{
    return request.post("/problem/deleteProblem",problemInfo)
}