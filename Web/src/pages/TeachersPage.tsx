import {HeaderComponent} from "../components/HeaderComponent.tsx";
import ListTeacherComponent from "../components/ListTeacherComponent.tsx";
import {Paper, Button, Group} from "@mantine/core";
import {Link} from "react-router-dom";

export default function TeachersPage() {
    return (
        <>
            <HeaderComponent active_page={1}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <ListTeacherComponent/>
                <Group  justify="end"><Link to={"/create-teacher"}><Button>Добавить Преподавателя</Button></Link></Group>
            </Paper>
        </>
    )
}
