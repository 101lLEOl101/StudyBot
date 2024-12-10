import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Button, Group, Paper} from "@mantine/core";
import ListGroupsComponent from "../components/ListGroupsComponent.tsx";
import {Link} from "react-router-dom";

export default function GroupsStudentsPage() {
    return (
        <>
            <HeaderComponent active_page={2}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <ListGroupsComponent/>
                <Group  justify="end"><Link to={"/create-group"}><Button>Добавить Группу</Button></Link></Group>
            </Paper>
        </>
    )
}
