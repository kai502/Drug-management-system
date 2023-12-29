import request from "@/utils/request"


export const getDrugs = (drugPage) =>{
    return request.post('drug/getDrugs',drugPage)
}

export const drugAdd = (drugInfo) =>{
    return request.post('drug/addDrug',drugInfo)
}

export const deleteDrug = (drugId) =>{
    return request.post('drug/deleteDrug',drugId)
}

export const getDrug = (drugId) => {
    return request.post('drug/getDrug',drugId)
}

export const updateDrug = (drugInfo) => {
    return request.post('drug/updateDrug',drugInfo)
}