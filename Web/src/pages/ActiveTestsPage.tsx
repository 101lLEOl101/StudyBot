import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import ActiveTestsComponent from "../components/ActiveTestsComponent.tsx";

export default function ActiveTestsPage() {
    return (
        <>
            <HeaderComponent active_page={0}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <ActiveTestsComponent/>
            </Paper>
        </>

    )
}
