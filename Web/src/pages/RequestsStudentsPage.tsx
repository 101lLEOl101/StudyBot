import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import RequestsStudentsComponent from "../components/RequestsStudentsComponent.tsx";

export default function RequestsStudentsPage() {
    return (
        <>
            <HeaderComponent active_page={2}/>
            <Paper maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <RequestsStudentsComponent/>
            </Paper>
        </>
    )
}
