import {Box, Button, Loader, Notification, Table, Text} from '@mantine/core';
import {Link} from "react-router-dom";
import {useQuery} from "@tanstack/react-query";
import {fetchGroups} from "../api/service.ts";

export default function ListGroupsComponent() {
    const {status, data, error } = useQuery(["groups"], fetchGroups);
    if(status === "loading") {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Loader size="lg"/>
            </Box>
        )
    } else if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        )
    }
    console.log(data.data);
    const groups = data.data.filter((group) => {
        return group.workers && group.workers.length > 0 && group.workers[0].toString() === localStorage.getItem("userId");
    });
    const rows = groups.map((item) => (
        <Table.Tr key={item.partyName}>
            <Table.Td ta={"left"}>
                <Link to={`/group-students/${item.id}`}>
                    <Button variant={"default"}>
                        <Text fz="sm" fw={500}>
                            {item.partyName}
                        </Text>
                    </Button>
                </Link>
            </Table.Td>
            <Table.Td ta={"right"}>
                <Text size="sm">
                    {item.subs.length}
                </Text>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta={"left"}>Название Группы</Table.Th>
                        <Table.Th ta={"right"}>Количество учеников</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}