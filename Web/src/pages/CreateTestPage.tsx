import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import {CreateTestComponent} from "../components/CreateTestComponent.tsx";

export default function CreateTestPage() {
    return (
        <>
            <HeaderComponent active_page={2}/>
            <Paper maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <CreateTestComponent/>
            </Paper>
        </>
    )
}
