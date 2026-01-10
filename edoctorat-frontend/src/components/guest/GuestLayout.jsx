import { Outlet } from "react-router"
import { Header } from "../../pages/Header"

export const GuestLayout = () => {
    return (
        <>
           <Header/>
           <Outlet/>
        </>
    )
}