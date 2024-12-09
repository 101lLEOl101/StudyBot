import {QueryClientProvider} from "@tanstack/react-query";
import {queryClient} from "./api/query-client.ts";
import {Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage.tsx";
import RegistrationPage from "./pages/RegistrationPage.tsx";
import ActiveTestsPage from "./pages/ActiveTestsPage.tsx";
import TeachersPage from "./pages/TeachersPage.tsx";
import GroupsStudentsPage from "./pages/GroupsStudentsPage.tsx";
import DisciplinesPage from "./pages/DisciplinesPage.tsx";
import TestsPage from "./pages/TestsPage.tsx";

export default function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <Routes>
                <Route path={"/active-tests"} element={<ActiveTestsPage/>}/>
                <Route path={"/teachers"} element={<TeachersPage/>}/>
                <Route path={"/groups-students"} element={<GroupsStudentsPage/>}/>
                <Route path={"/disciplines"} element={<DisciplinesPage/>}/>
                <Route path={"/tests"} element={<TestsPage/>}/>
                <Route path={"/login"} element={<LoginPage/>}/>
                <Route path={"/registration"} element={<RegistrationPage/>}/>
            </Routes>
        </QueryClientProvider>
    )
}
