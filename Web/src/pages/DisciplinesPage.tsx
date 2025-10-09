import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Button, Group, Paper} from "@mantine/core";
import ListDisciplinesComponent from "../components/ListDisciplinesComponent.tsx";
import {Link} from "react-router-dom";

export default function DisciplinesPage() {
    return (
        <>
            <HeaderComponent active_page={3}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <ListDisciplinesComponent/>
                <Group  justify="end"><Link to={"/create-discipline"}><Button>Добавить Дисциплину</Button></Link></Group>
            </Paper>
        </>
    )
}
