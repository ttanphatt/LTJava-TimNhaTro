import React from 'react';
import { Container } from 'react-bootstrap';
import "./style.scss"

const Footer = () => {
    return (
        <div className='text-center text-lg-left footer'> 
            <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
                &copy; {new Date().getFullYear()} Copyright:{' '}
                <a className='text-dark'>
                    Phat-Thuyen
                </a>
            </div>
       </div>
    )

}
export default Footer;
