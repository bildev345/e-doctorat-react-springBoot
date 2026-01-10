import { Outlet } from "react-router"
import { Header } from "../../pages/Header"
import { CandidatSidebar } from "./CandidatSidebar"

export const CandidatLayout = () => {
    return (
        <>
            <Header/>
            <div>
                <CandidatSidebar/>
                
                <Outlet/>  {/* le contenu qui va changer */}
            </div>

        </>
    )
}