import {HeaderComponent} from "../components/HeaderComponent.tsx";
import { Button, Group, Paper} from "@mantine/core";
import {Link, useParams} from "react-router-dom";
import GroupStudentsComponent from "../components/GroupStudentsComponent.tsx";

export default function GroupStudentsPage() {
    return (
        <>
            <HeaderComponent active_page={2}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <GroupStudentsComponent/>
                <Group  justify="end">
                    <Link to={`/requests-students/${useParams().id}`}>
                        <Button >Запросы Студентов</Button>
                    </Link>
                </Group>
            </Paper>
        </>
    )
}
