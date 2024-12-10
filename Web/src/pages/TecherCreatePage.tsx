import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {CreateTeacherComponent} from "../components/CreateTeacherComponent.tsx";
import {Paper} from "@mantine/core";

export default function CreateTeacherPage(){
    return (
        <>
            <HeaderComponent active_page={1}/>
            <Paper maw={800} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <CreateTeacherComponent/>
            </Paper>
        </>
    )
}
