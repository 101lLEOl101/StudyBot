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
import CreateTeacherPage from "./pages/TecherCreatePage.tsx";
import CreateDisciplinePage from "./pages/CreateDisciplinePage.tsx";
import CreateGroupPage from "./pages/CreateGroupPage.tsx";
import GroupStudentsPage from "./pages/GroupStudentsPage.tsx";
import RequestsStudentsPage from "./pages/RequestsStudentsPage.tsx";

export default function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <Routes>
                <Route path={"/active-tests"} element={<ActiveTestsPage/>}/>
                <Route path={"/teachers"} element={<TeachersPage/>}/>
                <Route path={"/group-students"} element={<GroupsStudentsPage/>}/>
                <Route path={"/disciplines"} element={<DisciplinesPage/>}/>
                <Route path={"/tests"} element={<TestsPage/>}/>
                <Route path={"/login"} element={<LoginPage/>}/>
                <Route path={"/registration"} element={<RegistrationPage/>}/>
                <Route path={"/create-teacher"} element={<CreateTeacherPage/>}/>
                <Route path={"/create-discipline"} element={<CreateDisciplinePage/>}/>
                <Route path={"/create-group"} element={<CreateGroupPage/>}/>
                <Route path={"/requests-students"}>
                    <Route path=":id" element={<RequestsStudentsPage/>}/>
                </Route>
                <Route path="/group-students">
                    <Route index element={<GroupsStudentsPage />} />
                    <Route path=":id" element={<GroupStudentsPage />} />
                </Route>
            </Routes>
        </QueryClientProvider>
    )
}
