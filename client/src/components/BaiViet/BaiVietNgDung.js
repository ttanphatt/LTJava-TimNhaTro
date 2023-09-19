import { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import { Image, Table } from "react-bootstrap";
import MySpinner from "../../layout/MySpinner";
import { MyUserContext } from "../../App";
import "../TrangCaNhan/TrangCaNhan.scss"

const BaiVietNgDung = ({ idNgdung }) => {
    const [bvietngdung, setBvietngdung] = useState(null);
    const [user] = useContext(MyUserContext);
    const nav = useNavigate();

    useEffect(() => {

        const loadBvietngdung = async () => {
            let { data } = await Apis.get(endpoints['bviet-ngdung'](idNgdung));
            setBvietngdung(data);
        }


        loadBvietngdung();

    }, [idNgdung]);

    // const deleteBaiViet = async (baiVietId) => {
    //     try {
    //         await Apis.delete(endpoints['xoabv'](baiVietId));
    //         setBvietngdung((prevBvietngdung) => prevBvietngdung.filter((c) => c.id !== baiVietId));
    //     } catch (error) {
    //         console.error("Lỗi khi xóa baiViet: ", error);
    //     }
    // };


    if (!bvietngdung)
        return <MySpinner />;
    return (
        <>
            <div className="tincanhan">
                {bvietngdung.map(c => {
                    return <>
                        <div className="tin">
                            <div className="tin-anh">
                                <Image src={c.hinhAnh} style={{ width: '100%' }}></Image>
                            </div>
                            <div className="tin-thongtin">
                                <h5><Link style={{ textDecoration: 'none' }} className='text-danger' to={`/thtin_bviet/${c.id}`}>{c.tenBaiViet}</Link></h5>
                                <Table >
                                    <tr>
                                        <th>Khu vực</th>
                                        <td>{c.phamViCanTim}</td>
                                    </tr>
                                    <tr>
                                        <th>Giá thuê</th>
                                        <td>{c.giaThue}</td>
                                    </tr>
                                    <tr>
                                        <th>Ngày đăng</th>
                                        <td>{c.ngayDang}</td>
                                    </tr>
                                    <tr>
                                        <th>Địa chỉ</th>
                                        <td>{c.diaChiCt}</td>
                                    </tr>
                                </Table>
                                <div className="buttoncanhan">
                                    <button><Link style={{ textDecoration: 'none' }} className='text-danger' to={`/capnhatbv/${c.id}`}>Cập nhật</Link></button>

                                    <button>Xóa</button>
                                </div>
                            </div>

                        </div>
                    </>
                })}
                <div>
                </div>
            </div>
        </>
    );
}

export default BaiVietNgDung;