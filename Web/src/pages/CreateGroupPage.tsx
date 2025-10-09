import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import {CreateGroupComponent} from "../components/CreateGroupComponent.tsx";

export default function CreateGroupPage() {
    return (
        <>
            <HeaderComponent active_page={2}/>
            <Paper maw={800} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <CreateGroupComponent/>
            </Paper>
        </>
    )
}
