import { useContext, useEffect, useState } from "react";
import {Link, useParams } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import { Container, Image } from "react-bootstrap";
import MySpinner from "../../layout/MySpinner";
import "./TrangCaNhan.scss";
import { MyUserContext } from "../../App";
import BaiVietNgDung from "../BaiViet/BaiVietNgDung";
import TrangChu from "../TrangChu";

const ThTinNgDung = () => {
    const [user] = useContext(MyUserContext);
    const [thtinngdung, setThtinNgdung] = useState(null);
    const { id } = useParams();

    useEffect(() => {

        const loadThtinNgdung = async () => {
            let { data } = await Apis.get(`${endpoints['thtin-ngdung']}${user.id}`);
            setThtinNgdung(data);
            console.log(data);
        }

        loadThtinNgdung();

    }, [id]);

    if(user===null){
        return <TrangChu/>
    }
    if (!thtinngdung)
        return <MySpinner />;
    return (
        <>
            <Container>
                <div className="trangcanhan">
                    <div className="col11">
                        <div className="phanngang">THÔNG TIN CÁ NHÂN </div>
                        <div className="thtincanhan">
                            <div className="thtincanhan-anh">
                                <center><Image roundedCircle src={thtinngdung.avatar} />  </center>
                            </div>
                            <center>
                                <h5>{thtinngdung.idLoaiTaiKhoan.tenLoaiTaiKhoan}</h5>
                                <p>{thtinngdung.tenNguoiDung}</p>
                                <p>{thtinngdung.sdt}</p>
                                <div className="buttoncanhan">
                                    <button>Cập nhật</button>
                                    <button><Link style={{textDecoration:'none'}} to="/doimatkhau">Đổi mật khẩu</Link></button>
                                </div>
                            </center>
                        </div>
                    </div>
                    <div className="col12"></div>
                    <div className="col13">
                        <div className="phanngang">TIN ĐĂNG CÁ NHÂN</div>                        
                        <BaiVietNgDung idNgdung={user.id}/>
                    </div>
                </div>
            </Container>
        </>
    );
}

export default ThTinNgDung;