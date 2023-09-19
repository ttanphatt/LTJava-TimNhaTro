import { Alert, Button, Container, Form, Image } from "react-bootstrap";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../../layout/MySpinner";
import { useNavigate, useParams } from "react-router-dom";
import { useContext, useEffect, useRef, useState } from "react";
import "../BaiViet/BViet.scss";
import { MyUserContext } from "../../App";
import Toast from 'react-bootstrap/Toast';

// toast.configure({
//     autoClose: 2000,
//     draggable: false,
//     position: toast.POSITION.TOP_LEFT
//   })
//   const notify = () => toast('CẬP NHẬT THÀNH CÔNG !')

const CapNhatBV = ({ baivietId }) => {
    const [user] = useContext(MyUserContext);
    const [thtinbviet, setThtinBviet] = useState(null);
    const { id } = useParams();
    const [err, setErr] = useState(null);
    const nav = useNavigate();

    const [tenBaiVietEdit, setTenBaiVietEdit] = useState("");
    const [noiDungEdit, setNoiDungEdit] = useState("");
    const [phamViEdit, setPhamViEdit] = useState("");
    const [soNguoiEdit, setSoNguoiEdit] = useState("");
    const [giaThueEdit, setGiaThueEdit] = useState("");
    const [dienTichEdit, setDienTichEdit] = useState("");
    const [diaChiEdit, setDiaChiEdit] = useState("");

    useEffect(() => {
        const loadThtinBviet = async () => {
            let { data } = await Apis.get(`${endpoints['thtin-bviet']} + ${id}`)
            setThtinBviet(data);
        }
        loadThtinBviet();
    }, [id]);

    useEffect(() => {
        if (thtinbviet) {
            setTenBaiVietEdit(thtinbviet.tenBaiViet);
            setNoiDungEdit(thtinbviet.noiDung);
            setPhamViEdit(thtinbviet.phamViCanTim);
            setSoNguoiEdit(thtinbviet.soNguoi);
            setGiaThueEdit(thtinbviet.giaThue);
            setDienTichEdit(thtinbviet.dienTich);
            setDiaChiEdit(thtinbviet.diaChiCt);
        }
    }, [thtinbviet]);


    const capnhatbv = (e, id) => {
        e.preventDefault();
        const process = async () => {
            const data = await Apis.post(endpoints['capnhatbv'](id), {
                "id": id,
                "noiDung": noiDungEdit,
                "tenBaiViet": tenBaiVietEdit,
                "phamViCanTim": phamViEdit,
                "soNguoi": soNguoiEdit,
                "giaThue": giaThueEdit,
                "dienTich": dienTichEdit,
                "diaChiCt": diaChiEdit
            });
            console.log(data);
            if (data.status === 200) {
                <>
                <Toast>
                    <Toast.Header>
                        <img src="holder.js/20x20?text=%20" className="rounded me-2" alt="" />
                        <strong className="me-auto">Bootstrap</strong>
                        <small>11 mins ago</small>
                    </Toast.Header>
                    <Toast.Body>Hello, world! This is a toast message.</Toast.Body>
                </Toast>
            </>
            nav(`/thtin-ngdung/${user.id}`);
                
            } else {
                <>
                <Toast>
                    <Toast.Header>
                        <img src="holder.js/20x20?text=%20" className="rounded me-2" alt="" />
                        <strong className="me-auto">Bootstrap</strong>
                        <small>11 mins ago</small>
                    </Toast.Header>
                    <Toast.Body>Cập nhật bài viết thất bại</Toast.Body>
                </Toast>
            </>
            }
            // if (data.status === 200) {

            //     nav("/");
            // } else
            //     setErr("Hệ thống bị lỗi!");
        };

        process();
    }

    return (
        <>
            <Container>
                <h1>CẬP NHẬT BÀI VIẾT</h1>
                <div className="thtinbviet">
                    <div className="thtin-col1">
                        <div className="thtin-anh">
                            {/* <Image src={thtinbviet.hinhAnh} style={{ style: "100%" }}></Image> */}
                        </div>
                        <div className="thtin-ndung">
                            {user.idLoaiTaiKhoan.id === 2 ? <>

                                <Form onSubmit={(e) => capnhatbv(e, thtinbviet.id)} >
                                    <Form.Group className="mb-3">
                                        <Form.Label>Tiêu đề bài viết</Form.Label>
                                        <Form.Control
                                            type="text"
                                            value={tenBaiVietEdit}
                                            onChange={e => setTenBaiVietEdit(e.target.value)}
                                            placeholder="Tiêu đề bài viết"
                                            required />
                                    </Form.Group>
                                    <Form.Group className="mb-3">
                                        <Form.Label>Ngày đăng</Form.Label>
                                        <Form.Control type="date" disabled />
                                    </Form.Group>
                                    <div className="formGroup1">
                                        <Form.Group className="mb-3 group11">
                                            <Form.Label>Khu vực</Form.Label>
                                            <Form.Control
                                                type="text"
                                                value={phamViEdit}
                                                onChange={e => setPhamViEdit(e.target.value)}
                                                placeholder="Khu vực"
                                                required />
                                        </Form.Group>
                                        <Form.Group className="mb-3 group12">
                                            <Form.Label>Số lượng</Form.Label>
                                            <Form.Control
                                                type="text"
                                                value={soNguoiEdit}
                                                onChange={e => setSoNguoiEdit(e.target.value)}
                                                placeholder="Số lượng"
                                                required />
                                        </Form.Group>
                                    </div >
                                    <div className="formGroup2">
                                        <Form.Group className="mb-3 group11">
                                            <Form.Label>Giá thuê</Form.Label>
                                            <Form.Control
                                                type="text"
                                                value={giaThueEdit}
                                                onChange={e => setGiaThueEdit(e.target.value)}
                                                placeholder="Giá thuê"
                                                required />
                                        </Form.Group>
                                        <Form.Group className="mb-3 group12">
                                            <Form.Label>Diện tích</Form.Label>
                                            <Form.Control
                                                type="text"
                                                value={dienTichEdit}
                                                onChange={e => setDienTichEdit(e.target.value)}
                                                placeholder="Diện tích"
                                                required />
                                        </Form.Group>
                                    </div>
                                    <Form.Group className="mb-3">
                                        <Form.Label>Địa chỉ chi tiết</Form.Label>
                                        <Form.Control
                                            type="text"
                                            value={diaChiEdit}
                                            onChange={e => setDiaChiEdit(e.target.value)}
                                            placeholder="Địa chỉ chi tiết"
                                            required />
                                    </Form.Group>

                                    <Form.Group className="mb-3">
                                        <Form.Label>Mô tả chi tiết</Form.Label>
                                        <Form.Control
                                            as="textarea" rows={5}
                                            value={noiDungEdit}
                                            onChange={e => setNoiDungEdit(e.target.value)}
                                            placeholder="Mô tả chi tiết"
                                            required />
                                    </Form.Group>
                                    <Button variant="info" type="submit">Cập nhật bài</Button>
                                </Form>
                            </> : <>
                                <Form onSubmit={(e) => capnhatbv(e, thtinbviet.id)}>
                                    <Form.Group className="mb-3">
                                        <Form.Label>Tiêu đề bài viết</Form.Label>
                                        <Form.Control
                                            type="text"
                                            value={tenBaiVietEdit}
                                            onChange={e => setTenBaiVietEdit(e.target.value)}
                                            placeholder="Tiêu đề bài viết"
                                            required />
                                    </Form.Group>
                                    <Form.Group className="mb-3">
                                        <Form.Label>Ngày đăng</Form.Label>
                                        <Form.Control type="date" disabled />
                                    </Form.Group>
                                    <Form.Group className="mb-3 group11">
                                        <Form.Label>Phạm vi cần tìm</Form.Label>
                                        <Form.Control
                                            type="text"
                                            value={phamViEdit}
                                            onChange={e => setPhamViEdit(e.target.value)}
                                            placeholder="Khu vực"
                                            required />
                                    </Form.Group>

                                    <Form.Group className="mb-3">
                                        <Form.Label>Mô tả chi tiết</Form.Label>
                                        <Form.Control
                                            as="textarea" rows={5}
                                            value={noiDungEdit}
                                            onChange={e => setNoiDungEdit(e.target.value)}
                                            placeholder="Mô tả chi tiết"
                                            required />
                                    </Form.Group>
                                    <Button variant="info" type="submit">Cập nhật bài</Button>
                                </Form>
                            </>}
                        </div>
                    </div>
                    <div className="thtin-col2"></div>
                    {/* <div className="thtin-col3">
                        <div className="thtin-tacgia">
                            <div className="tacgia-anh">
                                <img src={thtinbviet.idNguoiDung.avatar} />
                            </div>
                            <div className="tacgia-thongtin">
                                <center>
                                    <h4>{thtinbviet.idNguoiDung.tenNguoiDung}</h4>

                                    <h5>{thtinbviet.idNguoiDung.sdt}</h5>
                                </center>
                            </div>

                        </div>

                    </div> */}

                </div>

            </Container>


        </>

    );
}

export default CapNhatBV;