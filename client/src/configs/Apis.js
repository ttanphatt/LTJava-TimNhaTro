import axios from "axios";
import cookie from "react-cookies";


const SERVER_CONTEXT  = "/NhaTro";
const SERVER = "http://localhost:8080"

export const endpoints ={
    "baiviet": `${SERVER_CONTEXT}/api/baiviet/`,
    "xoabv": (id) => `${SERVER_CONTEXT}/api/baiviet/${id}`,
    "baiviet1": `${SERVER_CONTEXT}/api/getBaiViet2Type/1`,
    "baiviet2": `${SERVER_CONTEXT}/api/getBaiViet2Type/2`,
    "thtin-bviet" :`${SERVER_CONTEXT}/api/getThTinBViet/`,
    "dsachngdung" : `${SERVER_CONTEXT}/api/gettaikhoanbytentk/`,
    "dangnhap" :`${SERVER_CONTEXT}/api/dangnhap/`,
    "dangnhap2" :`${SERVER_CONTEXT}/api/dangnhap/`,
    "current-user":`${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "thtin-ngdung" : `${SERVER_CONTEXT}/api/getTTNgDung/`,
    "bviet-ngdung" :(id) => `${SERVER_CONTEXT}/api/getBVietByIdNgDung/${id}`,
    "dangbai" :`${SERVER_CONTEXT}/api/dangbai/`,
    "capnhatbv" :(id) => `${SERVER_CONTEXT}/api/updateBaiVietAPI/${id}` ,
    "doimatkhau":`${SERVER_CONTEXT}/api/doimatkhau/`,
    "binhluanBybaiviet" : `${SERVER_CONTEXT}/api/listBinhLuanByBV/`,
    "thembl": `${SERVER_CONTEXT}/api/binhluan/`,
    "capnhatbl": (id) => `${SERVER_CONTEXT}/api/binhluan/${id}`,
    "xoabl": (id) => `${SERVER_CONTEXT}/api/binhluan/${id}`,
    "listBV": `${SERVER_CONTEXT}/api/listBV/`,

}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization" : cookie.load("token")
        }
    })
}


export default axios.create({
    baseURL: SERVER
})