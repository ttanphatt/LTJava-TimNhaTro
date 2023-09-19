import { useContext, useRef, useState } from "react";
import "./DangBai.scss"
import { MyUserContext } from "../../App";
import { useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import { Button, Form } from "react-bootstrap";

const DangBai = () => {
    const [user, dispatch] = useContext(MyUserContext);
    
    const [baiviet, setBaiViet] = useState({
        "ten": "",
        "noidung": "",
        "phamvi": "",
        "soluong": "",
        "giathue": "",
        "dientich": "",
        "diachi": "",
        "userid": user.id,
        "usertypeId": user.idLoaiTaiKhoan.id
    });

    const [err, setErr] = useState(null);
    const hinhanh = useRef();
    const nav = useNavigate();

    const dangbai = (evt) => {
        evt.preventDefault();
        const process = async () => {
            let form = new FormData();
            for (let field in baiviet) {
                form.append(field, baiviet[field]);
                if (user.idLoaiTaiKhoan.id === 3) {

                } else if (user.idLoaiTaiKhoan.id === 2) {
                    form.append("hinhanh", hinhanh.current.files[0]);
                } else {
                    nav("/");
                }
            }

            let res = await Apis.post(endpoints['dangbai'], form);

            if (res.status === 201) {
                nav("/");
            } else
                setErr("Hệ thống bị lỗi!");
        }
        process();
    }

    const change = (evt, field) => {
        // setUser({...user, [field]: evt.target.value})
        setBaiViet(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    

    return <>
        <h1 className="text-center text-info">ĐĂNG BÀI VIẾT</h1>
        <div className="dangbai">
            <div className="container1">
                <div className="phanngang">THÔNG TIN BÀI ĐĂNG</div>
                <div className="thongtinbaidang">
                    {user.idLoaiTaiKhoan.id === 2 ? <>
                        <Form onSubmit={dangbai}>
                            <Form.Group className="mb-3">
                                <Form.Label>Tiêu đề bài viết</Form.Label>
                                <Form.Control type="text" onChange={(e) => change(e, "ten")} placeholder="Tiêu đề bài viết" required />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Ngày đăng</Form.Label>
                                <Form.Control type="date" disabled />
                            </Form.Group>
                            <div className="formGroup1">
                                <Form.Group className="mb-3 group11">
                                    <Form.Label>Khu vực</Form.Label>
                                    <Form.Control type="text" onChange={(e) => change(e, "phamvi")} placeholder="Khu vực" required />
                                </Form.Group>
                                <Form.Group className="mb-3 group12">
                                    <Form.Label>Số lượng</Form.Label>
                                    <Form.Control type="text" onChange={(e) => change(e, "soluong")} placeholder="Số lượng" required />
                                </Form.Group>
                            </div >
                            <div className="formGroup2">
                                <Form.Group className="mb-3 group11">
                                    <Form.Label>Giá thuê</Form.Label>
                                    <Form.Control type="text" onChange={(e) => change(e, "giathue")} placeholder="Giá thuê" required />
                                </Form.Group>
                                <Form.Group className="mb-3 group12">
                                    <Form.Label>Diện tích</Form.Label>
                                    <Form.Control type="text" onChange={(e) => change(e, "dientich")} placeholder="Diện tích" required />
                                </Form.Group>
                            </div>
                            <Form.Group className="mb-3">
                                <Form.Label>Địa chỉ chi tiết</Form.Label>
                                <Form.Control type="text" onChange={(e) => change(e, "diachi")} placeholder="Địa chỉ chi tiết" required />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Hình ảnh phòng trọ</Form.Label>
                                <Form.Control type="file" ref={hinhanh} />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Mô tả chi tiết</Form.Label>
                                <Form.Control as="textarea" rows={5} onChange={(e) => change(e, "noidung")} placeholder="Mô tả chi tiết" required />
                            </Form.Group>
                            <Button variant="info" type="submit">Đăng bài</Button>
                        </Form>

                    </> : <>
                        <Form onSubmit={dangbai}>
                            <Form.Group className="mb-3">
                                <Form.Label>Tiêu đề bài viết</Form.Label>
                                <Form.Control type="text" onChange={(e) => change(e, "ten")} placeholder="Tiêu đề bài viết" required />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Ngày đăng</Form.Label>
                                <Form.Control type="date" disabled />
                            </Form.Group>
                            <Form.Group className="mb-3 group11">
                                <Form.Label>Phạm vi cần tìm</Form.Label>
                                <Form.Control type="text" onChange={(e) => change(e, "phamvi")} placeholder="Khu vực" required />
                            </Form.Group>
                            <Form.Group className="mb-3">

                                <Form.Control type="file" hidden ref={hinhanh} value={null} />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Mô tả chi tiết</Form.Label>
                                <Form.Control as="textarea" rows={5} onChange={(e) => change(e, "noidung")} placeholder="Mô tả chi tiết" required />
                            </Form.Group>
                            <Button variant="info" type="submit">Đăng bài</Button>
                        </Form>
                    </>}
                </div>
            </div>
            <div className="container2"></div>
            <div className="container3">
                <div className="phanngang">HƯỚNG DẪN ĐĂNG BÀI</div>
                <div className="huongdandangbai">

                </div>
            </div>


        </div>
    </>
}
export default DangBai;