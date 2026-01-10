import { CandidatLayout } from "../components/candidat/CandidatLayout";
import { GuestLayout } from "../components/guest/GuestLayout";
import { InfosPerso } from "../pages/candidat/InfosPerso";
import { Notifications } from "../pages/candidat/Notifications";
import { Parcours } from "../pages/candidat/parcours";
import { Postuler } from "../pages/candidat/Postuler";
import { Home } from "../pages/Home";
import { Login } from "../pages/auth/Login";

export const routes = [
    {
        path : "/",
        element : <GuestLayout/>,
        children : [
            {
                index : true,
                element : <Home/>
            },
            {
                path : "login",
                element : <Login/>
            }
        ] 
    },
    {
        path : "/candidat",
        element : <CandidatLayout/>,
        children : [
            {
                index : true,
                element : <InfosPerso/>
            },
            {
                path : "parcours",
                element : <Parcours/>
            },
            {
                path : "postuler",
                element : <Postuler/>
            },
            {
                path : "notifications",
                element : <Notifications/>
            }
            
        ]

    },
    {

    },
    {

    }
]