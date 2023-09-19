import React, { useEffect, useState } from 'react';
import { Link, Navigate, useSearchParams } from 'react-router-dom';
import Apis, { endpoints } from '../configs/Apis';
import { Image, Table } from 'react-bootstrap';
import "./BaiViet/BViet.scss"
import { Pagination } from "@mui/material";

const PAGE_SIZE = 2;


const TimKiem = () => {
    const [baiviets, setBaiViets] = useState(null);
    const [q] = useSearchParams();
    const [start, setStart] = useState('');
    const [page, setPage] = useState(1);

    useEffect(() => {
        let loadBaiViets = async () => {
            try {
                let e = endpoints['listBV'];
                let kw = q.get('kw');
                if (kw !== null)
                    e = `${e}?kw=${kw}`;
                let res = await Apis.get(e);
                setBaiViets(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }
        loadBaiViets();
    }, [q])
    

    useEffect(() => {
        setStart((page - 1) * PAGE_SIZE)
    }, [page])

    if(baiviets===null)
        return <Navigate to="/" />
        
    return (
        <>
            {baiviets.slice(start, start + PAGE_SIZE).map(p => {
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
                                <button><Link style={{ textDecoration: 'none', color: 'black' }} to={`/thtin_bviet/${p.id}`}>Lưu tin</Link></button>

                            </div>
                        </div>
                    </div>
                </>
            })}
            <div className="ChangePage">
                <Pagination
                    count={Math.ceil(baiviets.length / PAGE_SIZE)}
                    showFirstButton
                    showLastButton
                    onChange={(e, p) => setPage(p)}
                />
            </div>

        </>
    );
};

export default TimKiem;