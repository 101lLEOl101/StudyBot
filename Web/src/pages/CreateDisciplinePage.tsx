import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import {CreateDisciplineComponent} from "../components/CreateDisciplineComponent.tsx";

export default function CreateDisciplinePage(){
    return (
        <>
            <HeaderComponent active_page={3}/>
            <Paper maw={800} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <CreateDisciplineComponent/>
            </Paper>
        </>
    )
}
