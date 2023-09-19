import { useEffect, useState } from "react";
import { Container, Image, Table } from "react-bootstrap";
import MySpinner from "../../layout/MySpinner";
import Apis, { endpoints } from "../../configs/Apis";
import "./BViet.scss"
import { Link } from "react-router-dom";
import { MyUserContext } from "../../App";
import { Pagination } from "@mui/material";

const BaiViet2 = () => {
    const [baiviet2, setBaiViet2] = useState(null);
    const [page, setPage] = useState(1);
    const [start, setStart] = useState('');
    const PAGE_SIZE = 2;

    useEffect(() => {
        let loadBaiviet2 = async () => {
            try {
                let res = await Apis.get(endpoints['baiviet2']);
                setBaiViet2(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadBaiviet2();
    }, []);

    useEffect(() => {
        setStart((page - 1) * PAGE_SIZE)
    }, [page])

    if (baiviet2 === null)
        return <MySpinner />
    return (
        <body className="body">
            <>
                <h1 className="text-center text-info">TIN TÌM TRỌ</h1>

                <Container className="container-bv">

                    {baiviet2.slice(start, start + PAGE_SIZE).map(p => {
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
                                        <button><Link style={{ textDecoration: 'none', color: 'black' }} to={`/thtin_bviet/${p.id}`}>Đọc thêm</Link></button>
                                    </div>
                                </div>
                            </div>

                        </>
                        // return <Col xs={12} md={3} className="mt-1">
                        //     <Card style={{ width: '18rem' }}>
                        //         <Card.Img variant="top" src={p.hinhAnh} />
                        //         <Card.Body>
                        //             <Card.Title><Link className='text-danger' to={`/thtin_bviet/${p.id}`}>{p.tenBaiViet}</Link></Card.Title>
                        //             <Button className="mr-1" variant="primary">Xem chi tiết</Button>
                        //         </Card.Body>
                        //     </Card>
                        // </Col>

                    })}
                    <div className="ChangePage">
                        <Pagination
                            count={Math.ceil(baiviet2.length / PAGE_SIZE)}
                            showFirstButton
                            showLastButton
                            onChange={(e, p) => setPage(p)}
                        />
                    </div>
                </Container>

            </>
        </body>
    )

}
export default BaiViet2;