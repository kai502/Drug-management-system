import request from "@/utils/request";


export const getSuppliers = (supplierInfo) => {
    return request.post("supplier/getSuppliers",supplierInfo)
}

export const addSupplier = (supplierInfo) => {
    return request.post("supplier/addSupplier",supplierInfo)
}

export const deleteSupplier = (supplierInfo) => {
    return request.post("supplier/deleteSupplier",supplierInfo)
}

export const getSupplierAll = () =>{
    return request.get("supplier/getSupplierAll")
}

export const getSupplier = (supplierInfo) => {
    return request.post("supplier/getSupplier",supplierInfo)
}

export const updateSupplier = (supplierInfo) => {
    return request.post("supplier/updateSupplier",supplierInfo)
}


