import { useContext, useEffect, useState } from "react";
import { Image, Container, Table } from "react-bootstrap";
import MySpinner from "../../layout/MySpinner";
import Apis, { endpoints } from "../../configs/Apis";
import { Link } from "react-router-dom";
import "./BViet.scss"
import { MyUserContext } from "../../App";
import { Pagination } from "@mui/material";


const PAGE_SIZE = 8;

const BaiViet1 = () => {
    const [baiviet1, setBaiViet1] = useState(null);
    const [user] = useContext(MyUserContext);
    const [page, setPage] = useState(1);
    const [start, setStart] = useState('');

    useEffect(() => {
        let loadBaiviet1 = async () => {
            try {
                let res = await Apis.get(endpoints['baiviet1']);
                setBaiViet1(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadBaiviet1();

    }, []);

    useEffect(() => {
        setStart((page - 1) * PAGE_SIZE)
    }, [page])

    if (baiviet1 === null)
        return <MySpinner />

    return (
        <body className="body">
            <>
                <h1 className="text-center text-info">TIN CHO THUÊ</h1>
                <Container className="container-bv">
                    {(user !== null && user.idLoaiTaiKhoan.id === 3) ? <>
                        {baiviet1.slice(start, start + PAGE_SIZE).map(p => {
                            return <>
                                <div className="itembv">
                                    <div className="itembv-anh">
                                        <Image src={p.hinhAnh}></Image>
                                    </div>
                                    <div className="itembv-thtin">
                                        <h5><Link style={{ textDecoration: 'none' }} className='bv-ten' to={`/thtin_bviet/${p.id}`}>{p.tenBaiViet}</Link></h5>
                                        <Table >
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>{p.phamViCanTim}</td>
                                            </tr>
                                            <tr>
                                                <th>Giá thuê:</th>
                                                <td>{p.giaThue}/tháng</td>
                                            </tr>
                                            <tr>
                                                <th>Diện tích:</th>
                                                <td>{p.dienTich}m2</td>
                                            </tr>
                                        </Table>
                                        <div className="groupbtn">
                                            <Link style={{ textDecoration: 'none'}} to={`/thtin_bviet/${p.id}`} className="docthem">Đọc thêm</Link>
                                            <Link style={{ textDecoration: 'none'}} to={`/thtin_bviet/${p.id}`} className="luutin">Lưu tin</Link>
                                        </div>
                                    </div>
                                </div>
                            </>
                        })}
                        <div className="ChangePage">
                            <Pagination
                                count={Math.ceil(baiviet1.length / PAGE_SIZE)}
                                showFirstButton
                                showLastButton
                                onChange={(e, p) => setPage(p)}
                            />
                        </div>
                    </> : <>

                        {baiviet1.slice(start, start + PAGE_SIZE).map(p => {
                            return <>
                                <div className="itembv">
                                    <div className="itembv-anh">
                                        <Image src={p.hinhAnh}></Image>
                                    </div>
                                    <div className="itembv-thtin">
                                        <Table >
                                            <h5><Link style={{ textDecoration: 'none' }} className='text-info' to={`/thtin_bviet/${p.id}`}>{p.tenBaiViet}</Link></h5>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>{p.phamViCanTim}</td>
                                            </tr>
                                            <tr>
                                                <th>Giá thuê:</th>
                                                <td>{p.giaThue}/tháng</td>
                                            </tr>
                                            <tr>
                                                <th>Diện tích:</th>
                                                <td>{p.dienTich}m2</td>
                                            </tr>
                                        </Table>
                                        <div className="btn-docthem">
                                            <button><Link style={{ textDecoration: 'none'}} to={`/thtin_bviet/${p.id}`}>Đọc thêm</Link></button>
                                        </div>
                                    </div>
                                </div>
                            </>
                        })}
                        <div style={{ margin: '0 auto' }} className="ChangePage">
                            <Pagination
                                count={Math.ceil(baiviet1.length / PAGE_SIZE)}
                                showFirstButton
                                showLastButton
                                onChange={(e, p) => setPage(p)}
                            />
                        </div>
                    </>}



                </Container>


            </>
        </body>
    )

}
export default BaiViet1;